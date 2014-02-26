package org.fenixedu.sdk;

import java.io.IOException;
import java.util.Properties;

import org.fenixedu.sdk.exception.FenixEduClientException;

public class ApplicationConfiguration {

    private final String baseUrl;
    private final String oauthConsumerKey;
    private final String oauthConsumerSecret;
    private final String callbackUrl;

    public static ApplicationConfiguration fromProperties(String propertiesFilename) {
        Properties props = new Properties();
        try {
            props.load(ApplicationConfiguration.class.getResourceAsStream(propertiesFilename));
            return new ApplicationConfiguration(props.getProperty("base.url"), props.getProperty("oauth.consumer.key"),
                    props.getProperty("oauth.consumer.secret"), props.getProperty("callback.url"));
        } catch (IOException e) {
            throw new FenixEduClientException("Could not load " + propertiesFilename + " file.", e);
        }
    }

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
