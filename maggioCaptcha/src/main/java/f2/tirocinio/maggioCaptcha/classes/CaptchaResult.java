package f2.tirocinio.maggioCaptcha.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaptchaResult {

	boolean success;
	String hostname;
	@JsonProperty("challenge_ts")
	String challengeTS;

	public CaptchaResult() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getChallengeTS() {
		return challengeTS;
	}

	public void setChallengeTS(String challengeTS) {
		this.challengeTS = challengeTS;
	}

}
