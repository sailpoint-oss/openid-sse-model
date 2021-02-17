package net.openid.sse.model;

public class CAEPCredentialChange extends CAEPBaseEvent {

    private static final String CREDENTIAL_TYPE = "credential_type";
    private static final String CHANGE_TYPE     = "change_type";

    public static class Builder extends CAEPBaseEvent.Builder<CAEPCredentialChange, CAEPCredentialChange.Builder> {

        private static final String FRIENDLY_NAME   = "friendly_name";
        private static final String X509_ISSUER     = "x509_issuer";
        private static final String X509_SERIAL     = "x509_serial";
        private static final String FIDO2_AAGUID    = "fido2_aaguid";

        @Override
        protected CAEPCredentialChange createObj() {return new CAEPCredentialChange();}
        protected CAEPCredentialChange.Builder getThis() { return this; }


        public Builder() {
            super(SSEventTypes.CAEP_CREDENTIAL_CHANGE);
        }

        public Builder credentialType(final CAEPCredentialType type) {
            super.members.put(CREDENTIAL_TYPE, type.toString());
            return thisObj;
        }

        public Builder changeType(final CAEPChangeType type) {
            super.members.put(CHANGE_TYPE, type.toString());
            return thisObj;
        }

        public Builder friendlyName(final String name) {
            super.members.put(FRIENDLY_NAME, name);
            return thisObj;
        }

        public Builder x509Issuer(final String issuer) {
            super.members.put(X509_ISSUER, issuer);
            return thisObj;
        }

        public Builder x509Serial(final String serial) {
            super.members.put(X509_SERIAL, serial);
            return thisObj;
        }

        public Builder fido2AAGuid(final String guid) {
            super.members.put(FIDO2_AAGUID, guid);
            return thisObj;
        }

    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        Utils.validateMember(this, CREDENTIAL_TYPE, CAEPCredentialType.class);
        Utils.validateMember(this, CHANGE_TYPE,     CAEPChangeType.class);
    }

}
