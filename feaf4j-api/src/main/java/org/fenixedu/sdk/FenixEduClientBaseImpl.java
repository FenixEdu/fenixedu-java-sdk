package org.fenixedu.sdk;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.api.FenixEduEndpoint;
import org.fenixedu.sdk.exception.FenixEduClientException;
import org.fenixedu.sdk.impl.StaticHttpClientBinder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class FenixEduClientBaseImpl implements FenixEduClientBase {

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
    }

    @Override
    public ApplicationConfiguration getApplicationConfiguration() {
        return config;
    }
}
