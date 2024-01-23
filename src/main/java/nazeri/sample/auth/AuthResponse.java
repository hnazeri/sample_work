package nazeri.sample.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
	
	@JsonProperty("email")
	private String email;
	@JsonProperty("accessToken")
	private String accessToken;

	public AuthResponse() { }
	
	public AuthResponse(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
