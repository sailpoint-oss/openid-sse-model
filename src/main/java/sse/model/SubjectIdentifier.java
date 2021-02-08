package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public class SubjectIdentifier extends JSONObject {

    // https://github.com/richanna/secevent/blob/master/draft-ietf-secevent-subject-identifiers.txt
    public static final String ACCOUNT_SUBJECT_IDENTIFIER_TYPE = "account";
    public static final String EMAIL_SUBJECT_IDENTIFIER_TYPE = "email";
    public static final String ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE = "iss-sub";
    public static final String PHONE_NUMBER_SUBJECT_IDENTIFIER_TYPE = "phone_number";
    public static final String ALIASES_SUBJECT_IDENTIFIER_TYPE = "aliases";

    // https://bitbucket.org/openid/risc/pull-requests/5 section 3.2
    public static final String SPAG_SUBJECT_IDENTIFIER_TYPE = "spag";
    public static final String USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE = "user-device-session";
    public static final String JWT_ID_SUBJECT_IDENTIFIER_TYPE = "jwt-id";
    public static final String SAML_ASSERTION_ID_SUBJECT_IDENTIFIER_TYPE = "saml-assertion-id";

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

        private final SubjectIdentifier claims = new SubjectIdentifier();

        public SubjectIdentifier.Builder issuer(final String iss) {
            final String subjectTypeClaim = (String) claims.getOrDefault(SUBJECT_TYPE_CLAIM, null);
            if (null != subjectTypeClaim && SAML_ASSERTION_ID_SUBJECT_IDENTIFIER_TYPE.equals(subjectTypeClaim)) {
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

        public SubjectIdentifier build() {
            return claims;
        }

    }
}



