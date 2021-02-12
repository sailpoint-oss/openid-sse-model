package net.openid.sse.model;

public class CAEPBaseEvent extends SSEvent {

    public static class Builder extends SSEvent.Builder {
        private static final String EVENT_TIMESTAMP_CLAIM    = "event_timestamp";
        private static final String INITIATING_ENTITY_MEMBER = "initiating_entity";
        private static final String REASON_ADMIN_MEMBER      = "reason_admin";
        private static final String REASON_USER_MEMBER       = "reason_user";
        private static final String TENANT_ID_MEMBER         = "tenant_id";

        public Builder() {
            super();
        }

        public Builder eventTimestamp(final long eventTimestamp) {
            super.members.put(EVENT_TIMESTAMP_CLAIM, eventTimestamp);
            return this;
        }

        public Builder initiatingEntity(final CAEPInitiatingEntity entity) {
            super.members.put(INITIATING_ENTITY_MEMBER, entity.toString());
            return this;
        }

        public Builder reasonAdmin(final String s) {
            super.members.put(REASON_ADMIN_MEMBER, s);
            return this;
        }

        public Builder reasonUser(final String s) {
            super.members.put(REASON_USER_MEMBER, s);
            return this;
        }

        public Builder tenantID(final String id) {
            super.members.put(TENANT_ID_MEMBER, id);
            return this;
        }

    }
}
