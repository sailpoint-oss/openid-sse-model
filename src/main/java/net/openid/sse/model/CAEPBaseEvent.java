package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public abstract class CAEPBaseEvent extends SSEvent {

    protected CAEPBaseEvent() {}

    public abstract static class Builder<T extends CAEPBaseEvent, B extends CAEPBaseEvent.Builder<T, B>>
            extends SSEvent.Builder<T, B> {
        protected static final String EVENT_TIMESTAMP_MEMBER   = "event_timestamp";
        protected static final String INITIATING_ENTITY_MEMBER = "initiating_entity";
        protected static final String REASON_ADMIN_MEMBER      = "reason_admin";
        protected static final String REASON_USER_MEMBER       = "reason_user";
        protected static final String TENANT_ID_MEMBER         = "tenant_id";

        protected Builder(final SSEventTypes eventType) {
            super(eventType);
        }

        public B eventTimestamp(final long eventTimestamp) {
            members.put(EVENT_TIMESTAMP_MEMBER, eventTimestamp);
            return thisObj;
        }

        public B initiatingEntity(final CAEPInitiatingEntity entity) {
            members.put(INITIATING_ENTITY_MEMBER, entity.toString());
            return thisObj;
        }

        public B reasonAdmin(final String s) {
            members.put(REASON_ADMIN_MEMBER, s);
            return thisObj;
        }

        public B reasonUser(final String s) {
            members.put(REASON_USER_MEMBER, s);
            return thisObj;
        }

        public B tenantID(final String id) {
            members.put(TENANT_ID_MEMBER, id);
            return thisObj;
        }

    }

    public void validateEventTimestamp() throws ValidationException {
        final SSEventTypes eventType = getEventType();
        if (null == eventType) {
            /* Unknown event type, not instantiated via a normal constructor. */
            return;
        }
        JSONObject members = (JSONObject) get(eventType.toString());
        if (null == members) {
            throw new ValidationException("CAEP Events must have a container Map whose key is the event type URI");
        }

        if (!members.containsKey(Builder.EVENT_TIMESTAMP_MEMBER)) {
            return;
        }
        Object eventTimestamp = members.get(Builder.EVENT_TIMESTAMP_MEMBER);

        if (!(eventTimestamp instanceof Long)) {
            throw new ValidationException("CAEP Events event_timestamp must be of type Long.");
        }
        Long eventTimestampL = (Long) eventTimestamp;
        if (eventTimestampL < 0) {
            throw new ValidationException("CAEP Events event_timestamp must be > 0.");
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        validateEventTimestamp();
    }



}
