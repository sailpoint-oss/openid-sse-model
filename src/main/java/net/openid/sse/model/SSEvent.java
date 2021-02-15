package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.Map;
import java.util.Objects;

/**
 * A Shared Signals Event (SSE Event).
 *
 * @author adam.hampton
 * @author matt.domsch
 *
 */
public abstract class SSEvent extends JSONObject {

	private static final String SUBJECT_MEMBER     = "subject";
	private static final String STATUS_MEMBER      = "status";
	private static final String REASON_MEMBER      = "reason";
	private static final String PROPERTIES_MEMBER  = "properties";
	private static final String IPADDRESS_MEMBER   = "ip_address";
	private static final String ID_MEMBER   	   = "id";

	private SSEventTypes eventType;

	public SSEventTypes getEventType() {
		return eventType;
	}

	public void setEventType(SSEventTypes eventType) {
		this.eventType = eventType;
	}

	protected SSEvent() {}

	protected abstract static class Builder<T extends SSEvent, B extends Builder<T, B>> {

		protected T members;
		protected B thisObj;

		protected Builder() {
			members = createObj();
			thisObj = getThis();
		}

		protected Builder(final SSEventTypes eventType) {
			members = createObj();
			thisObj = getThis();
			this.eventType(eventType);
		}

		public T build() {
			T t = createObj();
			t.setEventType(eventType);
			t.put(eventType.toString(), members);
			return t;
		}

		protected abstract T createObj();
		protected abstract B getThis();


		private SSEventTypes eventType;

		public B eventType(final SSEventTypes eventType) {
			this.eventType = eventType;
			return thisObj;
		}

		public B subject(final Map<String, Object> sub) {
			members.put(SUBJECT_MEMBER, sub);
			return thisObj;
		}

		public B status(final String status) {
			members.put(STATUS_MEMBER, status);
			return thisObj;
		}

		public B reason(final String reason) {
			members.put(REASON_MEMBER, reason);
			return thisObj;
		}

		public B properties(final Map<String, Object> properties) {
			members.put(PROPERTIES_MEMBER, properties);
			return thisObj;
		}

		public B ipAddress(final String ipAddress) {
			members.put(IPADDRESS_MEMBER, ipAddress);
			return thisObj;
		}

		public B id(final String ipAddress) {
			members.put(ID_MEMBER, ipAddress);
			return thisObj;
		}

		public B claim(final String claim, final Object o) {
			members.put(claim, o);
			return thisObj;
		}
	}

	private void validateEventTypeName() throws ValidationException {
		for (String k : this.keySet()) {
			if (SSEventTypes.contains(k))
				return;
		}
		throw new ValidationException("SSEvent eventTypeName not in SSEventTypes.");
	}

	private void validateSubjectPresent() throws ValidationException {
		if (null == eventType) {
			/* Unknown event type, not instantiated via a normal constructor. */
			return;
		}
		JSONObject members = (JSONObject) get(eventType.toString());
		if (null == members) {
			throw new ValidationException("SSE Events must have a container Map whose key is the event type URI");
		}

		if (!members.containsKey(SUBJECT_MEMBER)) {
			throw new ValidationException("SSE Events must include subject member.");
		}
	}


	public void validate() throws ValidationException {
		validateEventTypeName();
		validateSubjectPresent();
	}


	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		SSEvent event = (SSEvent) obj;
		return eventType.equals(event.getEventType());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this, eventType);
	}
}



