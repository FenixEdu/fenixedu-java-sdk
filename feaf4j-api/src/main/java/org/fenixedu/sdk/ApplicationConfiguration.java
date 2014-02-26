package org.fenixedu.sdk;

public class ApplicationConfiguration {

    private final String baseUrl;
    private final String oauthConsumerKey;
    private final String oauthConsumerSecret;
    private final String callbackUrl;

    public ApplicationConfiguration(String baseUrl, String oauthConsumerKey, String oauthConsumerSecret, String callbackUrl) {
        this.baseUrl = baseUrl;
        this.oauthConsumerKey = oauthConsumerKey;
        this.oauthConsumerSecret = oauthConsumerSecret;
        this.callbackUrl = callbackUrl;
    }

    /**
     * The base URL of the FenixEdu installation (without slash)
     * 
     * @return the base URL of the FenixEdu installation
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * The application's OAuth Consumer Key
     * 
     * @return the applications' OAuth Consumer Key
     */
    public String getOAuthConsumerKey() {
        return oauthConsumerKey;
    }

    /**
     * The application's OAuth Consumer Secret
     * 
     * @return the applications' OAuth Consumer Secret
     */
    public String getOAuthConsumerSecret() {
        return oauthConsumerSecret;
    }

    /**
     * The callback URL of this installation (without slash)
     * 
     * @return the callback URL of this installation
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

}
