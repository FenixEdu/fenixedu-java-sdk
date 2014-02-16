package pt.ist.fenixedu.sdk;

public class FenixEduUserConfig {
	
	private String username;
	private String accessToken;
	private String refreshToken;
	
	public FenixEduUserConfig(String username, String accessToken,
			String refreshToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public FenixEduUserConfig() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
