package sse.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SSEventProperties {

	@JsonProperty("token")
	String token;
	
	@JsonProperty("reason")
	String reason;
	
	@JsonProperty("cause_time")
	@JsonAlias("causeTime")
	String causeTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCauseTime() {
		return causeTime;
	}

	public void setCauseTime(String causeTime) {
		this.causeTime = causeTime;
	}
	
}
