package sse.model;

import net.minidev.json.JSONObject;

import java.util.Map;

/**
 * A Shared Signals Event (SSE Event).
 *
 * @author adam.hampton
 * @author matt.domsch
 *
 */
public class SSEvent  {

	private SSEvent() {}

	public static class Builder {

		private static final String SUBJECT_CLAIM     = "subject";
		private static final String STATUS_CLAIM      = "status";
		private static final String REASON_CLAIM      = "reason";
		private static final String PROPERTIES_CLAIM  = "properties";
		private static final String IPADDRESS_CLAIM   = "ip_address";
		private static final String ID_CLAIM   		  = "id";

		private final JSONObject claims = new JSONObject();

		public SSEvent.Builder subject(final Map<String, Object> sub) {
			claims.put(SUBJECT_CLAIM, sub);
			return this;
		}

		public SSEvent.Builder status(final String status) {
			claims.put(STATUS_CLAIM, status);
			return this;
		}

		public SSEvent.Builder reason(final String reason) {

			claims.put(REASON_CLAIM, reason);
			return this;
		}
		public SSEvent.Builder properties(final Map<String, Object> properties) {
			claims.put(PROPERTIES_CLAIM, properties);
			return this;
		}

		public SSEvent.Builder ipAddress(final String ipAddress) {
			claims.put(IPADDRESS_CLAIM, ipAddress);
			return this;
		}

		public SSEvent.Builder id(final String ipAddress) {
			claims.put(ID_CLAIM, ipAddress);
			return this;
		}

		public JSONObject build() { return claims; }
	}
}

