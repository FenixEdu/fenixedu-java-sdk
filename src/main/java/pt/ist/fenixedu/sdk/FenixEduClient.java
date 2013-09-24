package pt.ist.fenixedu.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Joiner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public final class FenixEduClient {

	private final FenixEduConfig config;

	private final Client client;

	public FenixEduClient() throws IOException {
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/fenixedu.config"));
		this.client = Client.create();
		this.config = new FenixEduConfig(
				props.getProperty(FenixEduConfig.CONSUMER_KEY_PROPERTY),
				props.getProperty(FenixEduConfig.CONSUMER_SECRET_PROPERTY),
				props.getProperty(FenixEduConfig.BASE_URL),
				props.getProperty(FenixEduConfig.CALLBACK_URL));
	}

	public String getAuthenticationUrl() {
		return config.getBaseUrl()
				+ "/external/oauth.do?method=getUserPermission&client_id="
				+ this.config.getConsumerKey() + "&redirect_uri="
				+ this.config.getCallbackUrl();
	}

	public void setCode(String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "getTokens");
		params.put("client_id", this.config.getConsumerKey());
		params.put("redirect_uri", this.config.getCallbackUrl());
		params.put("client_secret", this.config.getConsumerSecret());
		params.put("code", code);
		params.put("grant_type", "authorization_code");

		String token_url = config.getBaseUrl() + "/external/oauth.do";

		List<String> args = new ArrayList<String>();

		String[] headers = new String[] { "method", "client_id",
				"client_secret", "redirect_uri", "code", "grant_type" };

		for (String key : headers) {
			args.add(Joiner.on("=").join(key, params.get(key)));
		}
		String url_encoded = Joiner.on("&").join(args);
		WebResource resource = client.resource(token_url);
		String rsp = resource.entity(url_encoded,
				MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
		JsonObject response = new JsonParser().parse(rsp).getAsJsonObject();
		String accessToken = response.get("access_token").getAsString();
		String refreshToken = response.get("refresh_token").getAsString();
		config.setAccessToken(accessToken);
		config.setRefreshToken(refreshToken);
	}

	private JsonObject invoke(String endpoint, String httpMethod) {
		String appendChar = "?";
		if (endpoint.contains("?")) {
			appendChar = "&";
		}
		WebResource webResource = client.resource(config.getBaseUrl()
				+ "/jersey" + endpoint + appendChar + "access_token="
				+ config.getAccessToken());
		webResource.accept(MediaType.APPLICATION_JSON);
		String rsp = webResource.method(httpMethod, String.class);
		return new JsonParser().parse(rsp).getAsJsonObject();
	}

	private static final String PRIVATE_BASE = "/private/v1/";

	public JsonObject getPerson() {
		return invoke(PRIVATE_BASE + "person", HttpMethod.GET);
	}

	public JsonObject getPersonCourses(int semester, String year) {
		return invoke(PRIVATE_BASE + "person/courses?semester=" + semester
				+ "&year=" + year, HttpMethod.GET);
	}

	public FenixEduConfig getConfig() {
		return config;
	}
}
