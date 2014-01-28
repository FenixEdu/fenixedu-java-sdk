package pt.ist.fenixedu.sdk.auth;

import java.util.Map;

import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;

import com.sun.jersey.api.client.WebResource;

public class OAuthAuthorizationImpl implements OAuthAuthorization {

    private final Configuration config;

    private String oAuthAccessToken;
    private String oAuthRefreshToken;

    public OAuthAuthorizationImpl(Configuration config) {
        this.config = config;
    }

    @Override
    public String getOAuthConsumerKey() {
        return config.getOAuthConsumerKey();
    }

    @Override
    public String getOAuthConsumerSecret() {
        return config.getOAuthConsumerSecret();
    }

    @Override
    public String getOAuthAccessToken() {
        return oAuthAccessToken;
    }

    @Override
    public String getOAuthRefreshToken() {
        return oAuthRefreshToken;
    }

    @Override
    public WebResource authorize(WebResource webResource) {
        return webResource.queryParam("access_token", getOAuthAccessToken());
    }

    @Override
    public void setCredentials(Map<String, String> credentialMap) {
        this.oAuthAccessToken = credentialMap.get("access_token");
        this.oAuthRefreshToken = credentialMap.get("refresh_token");
    }

}
