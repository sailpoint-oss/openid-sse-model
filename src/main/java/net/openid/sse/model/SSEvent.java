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

		private static final String SUBJECT_MEMBER     = "subject";
		private static final String STATUS_MEMBER      = "status";
		private static final String REASON_MEMBER      = "reason";
		private static final String PROPERTIES_MEMBER  = "properties";
		private static final String IPADDRESS_MEMBER   = "ip_address";
		private static final String ID_MEMBER   	   = "id";

		private String eventTypeName = "";
		protected final JSONObject members = new JSONObject();

		public Builder eventType(final SSEventTypes eventType) {
			this.eventTypeName = eventType.toString();
			return this;
		}

		public Builder eventType(final String eventType) {
			this.eventTypeName = eventType;
			return this;
		}

		public Builder subject(final Map<String, Object> sub) {
			members.put(SUBJECT_MEMBER, sub);
			return this;
		}

		public Builder status(final String status) {
			members.put(STATUS_MEMBER, status);
			return this;
		}

		public Builder reason(final String reason) {

			members.put(REASON_MEMBER, reason);
			return this;
		}
		public Builder properties(final Map<String, Object> properties) {
			members.put(PROPERTIES_MEMBER, properties);
			return this;
		}

		public Builder ipAddress(final String ipAddress) {
			members.put(IPADDRESS_MEMBER, ipAddress);
			return this;
		}

		public Builder id(final String ipAddress) {
			members.put(ID_MEMBER, ipAddress);
			return this;
		}

		public Builder claim(final String claim, final Object o) {
			members.put(claim, o);
			return this;
		}

		public SSEvent build() {
			SSEvent event = new SSEvent();
			event.put(eventTypeName, members);
			return  event;
		}
	}
}

