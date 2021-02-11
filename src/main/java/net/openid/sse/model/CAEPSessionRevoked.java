package net.openid.sse.model;

public class CAEPSessionRevoked extends SSEvent {

    public static class Builder extends SSEvent.Builder {
        private static final String EVENT_TIMESTAMP_CLAIM = "event_timestamp";
        private static final String INITIATING_ENTITY_CLAIM = "initiating_entity";
        private static final String REASON_ADMIN_CLAIM = "reason_admin";
        private static final String REASON_USER_CLAIM = "reason_user";
        private static final String TENANT_ID_CLAIM = "tenant_id";

        public Builder eventTimestamp(final long eventTimestamp) {
            super.claims.put(EVENT_TIMESTAMP_CLAIM, eventTimestamp);
            return this;
        }

        public Builder initiatingEntity(final CAEPInitiatingEntity entity) {
            super.claims.put(INITIATING_ENTITY_CLAIM, entity.toString());
            return this;
        }

        public Builder reasonAdmin(final String s) {
            super.claims.put(REASON_ADMIN_CLAIM, s);
            return this;
        }

        public Builder reasonUser(final String s) {
            super.claims.put(REASON_USER_CLAIM, s);
            return this;
        }

        public Builder tenantID(final String id) {
            super.claims.put(TENANT_ID_CLAIM, id);
            return this;
        }

    }
}
