package sse.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A JSON Serialize-able container for a Security event Token, which 
 * is the envelope wrapper. 
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SEToken {
	
	/**
	 * The Issuer of the Security Event Token.  An HTTP URL identifying
	 * the originating system of the SET.  This can be used by receiving
	 * parties to:
	 *  1) Context-ualize their relationship with the sender. 
	 *  2) Identify any keys they have locally configured to verify sender.
	 *  3) Perform signature validation on the event from the sender. 
	 */
	@JsonProperty("iss")
	@JsonAlias("issuer")
	String issuer;
	
	/**
	 * Issued At - Unix time stamp of when the event was issued. 
	 */
	@JsonProperty("iat")
	@JsonAlias("issuedAt")
	long issuedAt;
	
	/**
	 * The "jti" (JWT ID) claim provides a unique identifier for the JWT.
	 */
	@JsonProperty("jti")
	@JsonAlias("jwtId")
	String jti;
	
	/**
	 * The intended recipient/audience for the JWT.
	 * 
	 *  If the principal processing the claim does not identify itself with 
	 *  a value in the "aud" claim when this claim is present, then the JWT 
	 *  MUST be rejected.  In the general case, the "aud" value is an array 
	 *  of case-sensitive strings, each containing a StringOrURI value.  In 
	 *  the special case when the JWT has one audience, the "aud" value MAY 
	 *  be a single case-sensitive string containing a StringOrURI value.  
	 *  The interpretation of audience values is generally application 
	 *  specific. Use of this claim is OPTIONAL.
	 */
	@JsonProperty("aud")
	@JsonAlias("audience")
	String audience;
	
	/**
	 * "exp" (Expiration Time) Claim
	 *  The "exp" (expiration time) claim identifies the expiration time on
	 *   or after which the JWT MUST NOT be accepted for processing.  The
	 *   processing of the "exp" claim requires that the current date/time
	 *   MUST be before the expiration date/time listed in the "exp" claim.
	 */
	@JsonProperty("exp")
	@JsonAlias("exipirationTime")
	String expirationTime;
	
	/**
	 * !! Deviation from the written standard for SSE/CAEP. !!
	 * This is used during debugging/scaffolding work for SSE/CAEP systems.
	 * 
	 * Network Participant - an identifier of which CAEP network agent is sending
	 * this event.  This is normally derived based on mutual TLS configured between
	 * two parties running certificate based identification.   This is an alternative
	 * method provided for clear-text HTTP and test cases.  
	 * 
	 */
	@JsonProperty("npt")
	@JsonAlias("networkParticipant")
	String networkParticipant;
	
	/**
	 * List of Shared Signals Events (SSEs) carried in the Security Event Token (SET).
	 */
	@JsonProperty("events")
	@JsonAlias("eventList")
	List<SSEvent> events;
	
	
	/**
	 * SailPoint specific property: The ID of the CaepSite record for the CAEP
	 * Site that originated and sent this message to SailPoint's system.
	 */
	String caepSiteId;

	public String getNetworkParticipant() {
		return networkParticipant;
	}

	public void setNetworkParticipant(String networkParticipant) {
		this.networkParticipant = networkParticipant;
	}

	public long getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(long issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getCaepSiteId() {
		return caepSiteId;
	}

	public void setCaepSiteId(String caepSiteId) {
		this.caepSiteId = caepSiteId;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public List<SSEvent> getEvents() {
		return events;
	}

	public void setEvents(List<SSEvent> events) {
		this.events = events;
	}
	
	public void addEvent(SSEvent event) {
		if (null == events) {
			events = new ArrayList<SSEvent>();
		}
		event.setEventType(event.getEventType() + "#" + events.size());
		events.add(event);
	}
	
}
