package net.openid.sse.model;

public class CAEPCredentialChange extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        private static final String CREDENTIAL_TYPE = "credential_type";
        private static final String CHANGE_TYPE     = "change_type";
        private static final String FRIENDLY_NAME   = "friendly_name";
        private static final String X509_ISSUER     = "x509_issuer";
        private static final String X509_SERIAL     = "x509_serial";
        private static final String FIDO2_AAGUID    = "fido2_aaguid";

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_CREDENTIAL_CHANGE);
        }

        public Builder credentialType(final String type) {
            super.members.put(CREDENTIAL_TYPE, type);
            return this;
        }

        public Builder changeType(final String type) {
            super.members.put(CHANGE_TYPE, type);
            return this;
        }

        public Builder friendlyName(final String name) {
            super.members.put(FRIENDLY_NAME, name);
            return this;
        }

        public Builder x509Issuer(final String issuer) {
            super.members.put(X509_ISSUER, issuer);
            return this;
        }

        public Builder x509Serial(final String serial) {
            super.members.put(X509_SERIAL, serial);
            return this;
        }

        public Builder fido2AAGuid(final String guid) {
            super.members.put(FIDO2_AAGUID, guid);
            return this;
        }

    }
}
