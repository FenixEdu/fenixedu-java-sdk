package pt.ist.fenixedu.sdk;

public class FenixEduConfig {

    public static final String CONSUMER_KEY = "fenixedu.consumer.key";
    public static final String CONSUMER_SECRET = "fenixedu.consumer.secret";
    public static final String ACCESS_TOKEN = "fenixedu.access.token";

    public static final String BASE_URL = "fenixedu.base.url";
    public static final String CALLBACK_URL = "fenixedu.callback.url";

    private final String consumerKey;
    private final String consumerSecret;

    private final String baseUrl;
    private final String callbackUrl;

    public FenixEduConfig(String consumerKey, String consumerSecret, String baseUrl, String callbackUrl) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.baseUrl = baseUrl;
        this.callbackUrl = callbackUrl;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

}
