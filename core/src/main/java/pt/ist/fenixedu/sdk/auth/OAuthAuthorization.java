package pt.ist.fenixedu.sdk.auth;

public interface OAuthAuthorization extends Authorization {

    String ACCESS_TOKEN = "access_token";
    String REFRESH_TOKEN = "refresh_token";

    String getOAuthConsumerKey();

    String getOAuthConsumerSecret();

    String getOAuthAccessToken();

    String getOAuthRefreshToken();

}
