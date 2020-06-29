package org.fenixedu.sdk;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.api.FenixEduEndpoint;
import org.fenixedu.sdk.exception.ExceptionFactory;
import org.fenixedu.sdk.exception.ApiClientException;
import org.fenixedu.sdk.impl.StaticHttpClientBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class FenixEduClientBaseImpl implements FenixEduClientBase {

    private static final Logger logger = LoggerFactory.getLogger(FenixEduClientBaseImpl.class);

    protected final ApplicationConfiguration config;

    private final HttpClient client;

    public FenixEduClientBaseImpl(ApplicationConfiguration config) throws ApiClientException {
        this.config = config;
        try {
            this.client = StaticHttpClientBinder.getSingleton().getHttpClientFactory().getHttpClient();
        } catch (final Throwable e) {
            throw new ApiClientException("Could not obtain HTTP client", e);
        }
    }

    public String getAuthenticationUrl() {
        final Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        final HttpRequest request =
                FenixEduApiRequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_USER_DIALOG).withQueryParams(queryParams);
        return request.getUrl();
    }

    public final FenixEduUserDetails getUserDetailsFromCode(String code) throws ApiClientException {
        final Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        queryParams.put("client_secret", getEncodedSecret());
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("code", code);
        queryParams.put("grant_type", "authorization_code");

        final HttpRequest httpRequest = FenixEduApiRequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_ACCESS_TOKEN)
                .withBody(Joiner.getEncodedQueryParams(queryParams));
        final ClientResponse response = client.handleHttpRequest(httpRequest);

        final String output = response.getResponse();
        final JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        if (response.getStatusCode() == Status.OK.getStatusCode()) {
            final String accessToken = jsonObject.get(OAuthAuthorization.ACCESS_TOKEN).getAsString();
            final String refreshToken = jsonObject.get(OAuthAuthorization.REFRESH_TOKEN).getAsString();
            return new FenixEduUserDetails(new OAuthAuthorizationImpl(accessToken, refreshToken));
        }
        throw new ApiClientException(response.getResponse(), null);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Authorization authorization, String... endpointArgs)
            throws ApiClientException {
        return invoke(endpoint, authorization, null, endpointArgs);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, String... endpointArgs) throws ApiClientException {
        return invoke(endpoint, null, null, endpointArgs);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Map<String, String> queryParams, String... endpointArgs)
            throws ApiClientException {
        return invoke(endpoint, null, queryParams, endpointArgs);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Authorization authorization, Map<String, String> queryParams,
            String... endpointArgs) throws ApiClientException {

        if (queryParams == null) {
            queryParams = new HashMap<String, String>();
        }
        queryParams.put("lang", config.getLocale().getLanguage() + "-" + config.getLocale().getCountry());

        final HttpRequest httpRequest =
                FenixEduApiRequestFactory.fromFenixEduEndpoint(config, endpoint, endpointArgs).withQueryParams(queryParams);
        if (authorization != null) {
            httpRequest.withAuthorization(authorization);
        }
        final ClientResponse clientResponse = client.handleHttpRequest(httpRequest);
        if (clientResponse.getStatusCode() == 401) {
            final JsonObject jsonResponse = new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
            final String error = jsonResponse.get("error").getAsString();
            final String errorDescription = jsonResponse.get("error_description").getAsString();
            throw ExceptionFactory.createException(error, errorDescription);
        }
        if (endpoint.getResponseClass().equals(JsonArray.class)) {
            return (T) new JsonParser().parse(clientResponse.getResponse()).getAsJsonArray();
        } else if (endpoint.getResponseClass().equals(JsonObject.class)) {
            return (T) new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
        } else if (endpoint.getResponseClass().equals(File.class)) {
            return (T) clientResponse.getResponse().getBytes();
        } else {
            throw new ApiClientException("Could not identify return type", null);
        }

    }

    public OAuthAuthorization refreshAccessToken(OAuthAuthorization authorization) {
        logger.debug("Refreshing OAuth Access Token using Refresh Token");
        final Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("grant_type", "refresh_token");
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        queryParams.put("client_secret", getEncodedSecret());
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("refresh_token", authorization.getOAuthRefreshToken());

        final HttpRequest httpRequest = FenixEduApiRequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_REFRESH_ACCESS_TOKEN)
                .withBody(Joiner.getEncodedQueryParams(queryParams));

        final ClientResponse clientResponse = client.handleHttpRequest(httpRequest);
        if (clientResponse.getStatusCode() == 400 || clientResponse.getStatusCode() == 401) {
            final JsonObject jsonResponse = new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
            final String error = jsonResponse.get("error").getAsString();
            throw ExceptionFactory.createException(error, null);
        }
        if (FenixEduEndpoint.OAUTH_REFRESH_ACCESS_TOKEN.getResponseClass().equals(JsonObject.class)) {
            final JsonObject responseJson = new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
            final String newAccessToken = responseJson.get("access_token").getAsString();
            return new OAuthAuthorizationImpl(newAccessToken, authorization.getOAuthRefreshToken());
        } else {
            throw new ApiClientException("Unexpected return type", null);
        }
    }

    private String getEncodedSecret() {
        String secret;
        try {
            secret = URLEncoder.encode(this.config.getOAuthConsumerSecret(), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            secret = this.config.getOAuthConsumerSecret();
        }
        return secret;
    }

    @Override
    public ApplicationConfiguration getApplicationConfiguration() {
        return config;
    }
}
