package org.fenixedu.sdk;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.api.FenixEduEndpoint;
import org.fenixedu.sdk.exception.FenixEduClientException;
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

    public FenixEduClientBaseImpl(ApplicationConfiguration config) {
        this.config = config;
        this.client = StaticHttpClientBinder.getSingleton().getHttpClientFactory().getHttpClient();
    }

    public String getAuthenticationUrl() {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        HttpRequest request =
                RequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_USER_DIALOG).withQueryParams(queryParams);
        return request.getUrl();
    }

    public final FenixEduUserDetails getUserDetailsFromCode(String code) throws FenixEduClientException {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        queryParams.put("client_secret", this.config.getOAuthConsumerSecret());
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("code", code);
        queryParams.put("grant_type", "authorization_code");

        HttpRequest httpRequest =
                RequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_ACCESS_TOKEN).withBody(
                        Joiner.getEncodedQueryParams(queryParams));
        ClientResponse response = client.handleHttpRequest(httpRequest);

        String output = response.getResponse();
        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        if (response.getStatusCode() == Status.OK.getStatusCode()) {
            String accessToken = jsonObject.get(OAuthAuthorization.ACCESS_TOKEN).getAsString();
            String refreshToken = jsonObject.get(OAuthAuthorization.REFRESH_TOKEN).getAsString();
            return new FenixEduUserDetails(new OAuthAuthorizationImpl(accessToken, refreshToken));
        }
        throw new FenixEduClientException(response.getResponse(), null);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Authorization authorization, String... endpointArgs)
            throws FenixEduClientException {
        return invoke(endpoint, authorization, null, endpointArgs);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, String... endpointArgs) throws FenixEduClientException {
        return invoke(endpoint, null, null, endpointArgs);
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Map<String, String> queryParams, String... endpointArgs)
            throws FenixEduClientException {
        return invoke(endpoint, null, null, endpointArgs);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Authorization authorization,
            Map<String, String> queryParams, String... endpointArgs) throws FenixEduClientException {
        HttpRequest httpRequest =
                RequestFactory.fromFenixEduEndpoint(config, endpoint, endpointArgs).withQueryParams(queryParams);
        if (authorization != null) {
            httpRequest.withAuthorization(authorization);
        }
        try {
            ClientResponse clientResponse = client.handleHttpRequest(httpRequest);
            if (endpoint.getResponseClass().equals(JsonArray.class)) {
                return (T) new JsonParser().parse(clientResponse.getResponse()).getAsJsonArray();
            } else if (endpoint.getResponseClass().equals(JsonObject.class)) {
                return (T) new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
            } else if (endpoint.getResponseClass().equals(File.class)) {
                return (T) clientResponse.getResponse().getBytes();
            } else {
                throw new FenixEduClientException("Could not identify return type", null);
            }
        } catch (FenixEduClientException e) {
            //TODO: Maybe use inheritance to differentiate exception
            if (e.getError().equals("accessTokenExpired")) {
                Authorization newAuthorization = refreshAccessToken(authorization);
                return invoke(endpoint, newAuthorization, queryParams, endpointArgs);
            }
            throw e;
        }
    }

    public Authorization refreshAccessToken(Authorization authorization) {
        logger.debug("Refreshing OAuth Access Token using Refresh Token");
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("grant_type", "refresh_token");
        queryParams.put("client_id", this.config.getOAuthConsumerKey());
        queryParams.put("client_secret", this.config.getOAuthConsumerSecret());
        queryParams.put("redirect_uri", this.config.getCallbackUrl());
        queryParams.put("refresh_token", authorization.asOAuthAuthorization().getOAuthRefreshToken());

        HttpRequest httpRequest =
                RequestFactory.fromFenixEduEndpoint(config, FenixEduEndpoint.OAUTH_REFRESH_ACCESS_TOKEN).withBody(
                        Joiner.getEncodedQueryParams(queryParams));
        System.out.println(httpRequest.getUrl());
        System.out.println(new String(httpRequest.getBody()));
        ClientResponse clientResponse = client.handleHttpRequest(httpRequest);
        if (FenixEduEndpoint.OAUTH_REFRESH_ACCESS_TOKEN.getResponseClass().equals(JsonObject.class)) {
            JsonObject responseJson = new JsonParser().parse(clientResponse.getResponse()).getAsJsonObject();
            String newAccessToken = responseJson.get("access_token").getAsString();
            return new OAuthAuthorizationImpl(newAccessToken, authorization.asOAuthAuthorization().getOAuthRefreshToken());
        } else {
            throw new FenixEduClientException("Unexpected return type", null);
        }
    }

    @Override
    public ApplicationConfiguration getApplicationConfiguration() {
        return config;
    }
}
