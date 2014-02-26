package org.fenixedu.sdk;

public class OAuthAuthorizationImpl implements OAuthAuthorization {

    private final String oAuthAccessToken;
    private final String oAuthRefreshToken;

    public OAuthAuthorizationImpl(String oAuthAccessToken, String oAuthRefreshToken) {
        this.oAuthAccessToken = oAuthAccessToken;
        this.oAuthRefreshToken = oAuthRefreshToken;
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
    public HttpRequest authorize(HttpRequest httpRequest) {
        return httpRequest.withQueryParam(OAuthAuthorization.ACCESS_TOKEN, getOAuthAccessToken());
    }

    @Override
    public OAuthAuthorization asOAuthAuthorization() {
        return this;
    }

}
