package sse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A JSON Serialize-able container for a Shared Signals Event (SSE Event).
 * 
 * @author adam.hampton
 *
 */
@JsonPropertyOrder({ "iss", "iat", "sub", "npt", "evt"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SSEvent {
	
	/**
	 * The subject of the SSE Event.
	 */
	@JsonProperty("subject")
	@JsonAlias("sub")
	Subject subject;
	
	/**
	 * The type of Shared Signals and Events (SSE) event.  
	 * This is defined in an enumeration (TODO: reference).
	 * This can be extended by implementers to include their own types
	 * of events.
	 */
	@JsonProperty("type")
	@JsonAlias({"event_type", "eventType"})
	String eventType;
	
	/**
	 * Currently a single-string placeholder for the event that may some day expand
	 * into a much larger JSON Object of its own. Defines the event that has occurred
	 * to the subject.
	 */
	@JsonProperty("evt")
	@JsonAlias("event")
	String event;
	
	/** 
	 * Carries the IP address for the IP address changed event. 
	 * Not clear if this is the new or old IP address for the subject.
	 * From example events on page 7.
	 */
	@JsonProperty("ip_address")
	@JsonAlias("ipAddress")
	String ipAddress; 
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public void setEventType(SSEventTypes sseType) {
		this.eventType = sseType.toString();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
