package pt.ist.fenixedu.sdk;

public class FenixEduConfig {

	public static final String CONSUMER_KEY_PROPERTY = "fenixedu.consumer.key";
	public static final String CONSUMER_SECRET_PROPERTY = "fenixedu.consumer.secret";

	public static final String BASE_URL = "fenixedu.base.url";
	public static final String CALLBACK_URL = "fenixedu.callback.url";

	private final String consumerKey;
	private final String consumerSecret;

	private final String baseUrl;
	private final String callbackUrl;

	private String accessToken;
	private String refreshToken;

	public FenixEduConfig(String consumerKey, String consumerSecret,
			String baseUrl, String callbackUrl) {
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

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

}
