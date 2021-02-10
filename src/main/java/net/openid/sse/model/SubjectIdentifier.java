package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public class SubjectIdentifier extends JSONObject {

    public static class Builder {

        private static final String SUBJECT_TYPE_CLAIM = "subject_type";
        private static final String ISSUER_CLAIM = "iss";
        private static final String SUBJECT_CLAIM = "sub";
        private static final String EMAIL_CLAIM = "email";
        private static final String PHONE_CLAIM = "phone_number";
        private static final String SPAG_ID_CLAIM = "spag_id";
        private static final String JWT_ID_CLAIM = "jti";
        private static final String SAML_ISSUER_CLAIM = "issuer";
        private static final String SAML_ASSERTION_ID_CLAIM = "assertion_id";
        private static final String USER_CLAIM = "user";
        private static final String DEVICE_CLAIM = "device";

        private final SubjectIdentifier claims = new SubjectIdentifier();

        public SubjectIdentifier.Builder issuer(final String iss) {
            final String subjectTypeClaim = (String) claims.getOrDefault(SUBJECT_TYPE_CLAIM, null);
            if (SubjectIdentifierTypes.SAML_ASSERTION_ID.equalsName(subjectTypeClaim)) {
                claims.put(SAML_ISSUER_CLAIM, iss);
            }
            else {
                claims.put(ISSUER_CLAIM, iss);
            }
            return this;
        }

        public SubjectIdentifier.Builder subjectType(final String subjectType) {
            claims.put(SUBJECT_TYPE_CLAIM, subjectType);
            return this;
        }

        public SubjectIdentifier.Builder subjectType(final SubjectIdentifierTypes subjectType) {
            claims.put(SUBJECT_TYPE_CLAIM, subjectType.toString());
            return this;
        }


        public SubjectIdentifier.Builder subject(final String sub) {

            claims.put(SUBJECT_CLAIM, sub);
            return this;
        }

        public SubjectIdentifier.Builder email(final String email) {

            claims.put(EMAIL_CLAIM, email);
            return this;
        }

        public SubjectIdentifier.Builder phoneNumber(final String phoneNumber) {

            claims.put(PHONE_CLAIM, phoneNumber);
            return this;
        }

        public SubjectIdentifier.Builder spagID(final String spag_id) {

            claims.put(SPAG_ID_CLAIM, spag_id);
            return this;
        }

        public SubjectIdentifier.Builder jwtID(final String jwt_id) {

            claims.put(JWT_ID_CLAIM, jwt_id);
            return this;
        }

        public SubjectIdentifier.Builder samlAssertionID(final String assertionId) {

            claims.put(SAML_ASSERTION_ID_CLAIM, assertionId);
            return this;
        }
        public SubjectIdentifier.Builder user(final SubjectIdentifier user) {
            claims.put(USER_CLAIM, user);
            return this;
        }

        public SubjectIdentifier.Builder device(final SubjectIdentifier device) {
            claims.put(DEVICE_CLAIM, device);
            return this;
        }

        public SubjectIdentifier.Builder claim(final String name, final Object value) {
            claims.put(name, value);
            return this;
        }

        public SubjectIdentifier build() {
            return claims;
        }

    }
}



