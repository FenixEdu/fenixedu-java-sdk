package pt.ist.fenixedu.sdk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import pt.ist.fenixedu.sdk.api.FenixEduEndpoint;
import pt.ist.fenixedu.sdk.auth.Authorization;
import pt.ist.fenixedu.sdk.auth.OAuthAuthorization;
import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;

public abstract class FenixEduClientBaseImpl implements FenixEduClientBase {

    protected final Configuration config;

    protected final Authorization authorization;

    private final Client client;

    public FenixEduClientBaseImpl(Configuration config, Authorization authorization) throws IOException {
        this.config = config;
        this.authorization = authorization;
        this.client = Client.create();
    }

    public String getAuthenticationUrl() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("client_id", this.config.getOAuthConsumerKey());
        paramMap.put("redirect_uri", this.config.getCallbackUrl());

        String url = String.format("%s/oauth/userdialog", config.getBaseUrl());
        String[] params = new String[] { "client_id", "redirect_uri" };

        List<String> queryParams = new ArrayList<String>();
        for (String param : params) {
            queryParams.add(Joiner.on("=").join(param, paramMap.get(param)));
        }
        WebResource resource = client.resource(url + "?" + Joiner.on("&").join(queryParams));
        String authenticationUrl = resource.getURI().toString();
        return authenticationUrl;
    }

    public void setCode(String code) throws FenixEduClientException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.config.getOAuthConsumerKey());
        params.put("redirect_uri", this.config.getCallbackUrl());
        params.put("client_secret", this.config.getOAuthConsumerSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String token_url = String.format("%s/oauth/access_token", this.config.getBaseUrl());

        List<String> args = new ArrayList<String>();

        String[] headers = new String[] { "client_id", "client_secret", "redirect_uri", "code", "grant_type" };

        for (String key : headers) {
            args.add(Joiner.on("=").join(key, params.get(key)));
        }
        String url_encoded = Joiner.on("&").join(args);
        WebResource resource = client.resource(token_url);
        ClientResponse rsp = resource.entity(url_encoded, MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class);

        if (rsp.getStatus() == Status.OK.getStatusCode()) {
            String output = rsp.getEntity(String.class);
            JsonObject response = new JsonParser().parse(output).getAsJsonObject();
            String accessToken = response.get(OAuthAuthorization.ACCESS_TOKEN).getAsString();
            String refreshToken = response.get(OAuthAuthorization.REFRESH_TOKEN).getAsString();
            Map<String, String> credentialMap = new HashMap<String, String>();
            credentialMap.put(OAuthAuthorization.ACCESS_TOKEN, accessToken);
            credentialMap.put(OAuthAuthorization.REFRESH_TOKEN, refreshToken);
            authorization.setCredentials(credentialMap);
        } else if (rsp.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
            throw new FenixEduClientException(rsp.getEntity(String.class), null);
        }
    }

    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Object... endpointArgs) throws FenixEduClientException {
        return invoke(endpoint, null, endpointArgs);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T invoke(FenixEduEndpoint endpoint, Map<String, String> queryParams, Object... endpointArgs)
            throws FenixEduClientException {
        WebResource webResource = client.resource(endpoint.generateEndpoint(endpointArgs));
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                webResource = webResource.queryParam(key, queryParams.get(key));
            }
        }
        webResource = authorization.authorize(webResource);
        String rsp = webResource.method(endpoint.getHttpMethod(), String.class);
        if (endpoint.getResponseClass().equals(JsonArray.class)) {
            return (T) new JsonParser().parse(rsp).getAsJsonArray();
        } else if (endpoint.getResponseClass().equals(JsonObject.class)) {
            return (T) new JsonParser().parse(rsp).getAsJsonObject();
        } else if (endpoint.getResponseClass().equals(File.class)) {
            return (T) rsp.getBytes();
        } else {
            throw new FenixEduClientException("Could not identify return type", null);
        }
    }

    @Override
    public Authorization getAuthorization() {
        return authorization;
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }

}
