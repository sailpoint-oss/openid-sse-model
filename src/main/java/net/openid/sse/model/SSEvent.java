package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.Map;

/**
 * A Shared Signals Event (SSE Event).
 *
 * @author adam.hampton
 * @author matt.domsch
 *
 */
public class SSEvent extends JSONObject {

	public static class Builder {

		private static final String SUBJECT_CLAIM     = "subject";
		private static final String STATUS_CLAIM      = "status";
		private static final String REASON_CLAIM      = "reason";
		private static final String PROPERTIES_CLAIM  = "properties";
		private static final String IPADDRESS_CLAIM   = "ip_address";
		private static final String ID_CLAIM   		  = "id";

		private String eventTypeName = "";
		protected final JSONObject claims = new JSONObject();

		public Builder eventType(final SSEventTypes eventType) {
			this.eventTypeName = eventType.toString();
			return this;
		}

		public Builder eventType(final String eventType) {
			this.eventTypeName = eventType;
			return this;
		}

		public Builder subject(final Map<String, Object> sub) {
			claims.put(SUBJECT_CLAIM, sub);
			return this;
		}

		public Builder status(final String status) {
			claims.put(STATUS_CLAIM, status);
			return this;
		}

		public Builder reason(final String reason) {

			claims.put(REASON_CLAIM, reason);
			return this;
		}
		public Builder properties(final Map<String, Object> properties) {
			claims.put(PROPERTIES_CLAIM, properties);
			return this;
		}

		public Builder ipAddress(final String ipAddress) {
			claims.put(IPADDRESS_CLAIM, ipAddress);
			return this;
		}

		public Builder id(final String ipAddress) {
			claims.put(ID_CLAIM, ipAddress);
			return this;
		}

		public Builder claim(final String claim, final Object o) {
			claims.put(claim, o);
			return this;
		}

		public SSEvent build() {
			SSEvent event = new SSEvent();
			event.put(eventTypeName, claims);
			return  event;
		}
	}
}

