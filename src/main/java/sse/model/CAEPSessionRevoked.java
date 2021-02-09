package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public class CAEPSessionRevoked extends JSONObject {

    public static class Builder {
        private static final String EVENT_TIMESTAMP_CLAIM = "event_timestamp";
        private static final String SUBJECT_CLAIM = "subject";
        private static final String INITIATING_ENTITY_CLAIM = "initiating_entity";
        private static final String REASON_ADMIN_CLAIM = "reason_admin";
        private static final String REASON_USER_CLAIM = "reason_user";
        private static final String TENANT_ID_CLAIM = "tenant_id";

        private final CAEPSessionRevoked claims = new CAEPSessionRevoked();

        public Builder eventTimestamp(final long eventTimestamp) {
            claims.put(EVENT_TIMESTAMP_CLAIM, eventTimestamp);
            return this;
        }

        public Builder subject(final SubjectIdentifier subject) {
            claims.put(SUBJECT_CLAIM, subject);
            return this;
        }

        public Builder initiatingEntity(final String entity) {
            claims.put(INITIATING_ENTITY_CLAIM, entity);
            return this;
        }

        public Builder reasonAdmin(final String s) {
            claims.put(REASON_ADMIN_CLAIM, s);
            return this;
        }

        public Builder reasonUser(final String s) {
            claims.put(REASON_USER_CLAIM, s);
            return this;
        }

        public Builder tenantID(final String id) {
            claims.put(TENANT_ID_CLAIM, id);
            return this;
        }

        public CAEPSessionRevoked build() {
            return claims;
        }
    }
}
