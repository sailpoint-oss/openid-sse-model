package sse.model;

import com.nimbusds.jwt.JWTClaimsSet;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A Security Events Token consisting of the base claims of a JWT,
 * plus SET-specific claims, and the ability to parse and unparse them.
 *
 */
public class SEToken {

	private JWTClaimsSet baseClaimSet;

	/**
	 * List of Shared Signals Events (SSEs) carried in the Security Event Token (SET).
	 */
	private static final String EVENTS_CLAIM = "events";
	private ArrayList<SSEvent> events;

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
	private static final String NETWORK_PARTICIPANT_CLAIM = "npt";

	/**
	 * SailPoint specific property: The ID of the CaepSite record for the CAEP
	 * Site that originated and sent this message to SailPoint's system.
	 */
	private static final String CAEPSITE_ID_CLAIM = "caepSiteId";

	public List<SSEvent> getEvents() { return events;}

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

	public static class Builder {
		private final Map<String,Object> claims = new LinkedHashMap<>();

		public Builder() {
			return JWTClaimsSet.Builder();
		}

	public Builder events


	}



}
