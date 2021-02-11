package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import static net.openid.sse.model.SubjectIdentifierClaims.*;

public class SubjectIdentifier extends JSONObject {

    public final String get(SubjectIdentifierClaims claim) {
        final String s = claim.toString();
        return (String) this.get(s);
    }

    public void put(final SubjectIdentifierClaims claim, Object o) {
        final String s = claim.toString();
        this.put(s, o);
    }

    public void validateSubjectType() throws ValidationException {
        final String subjectType = this.get(SUBJECT_TYPE);
        if (null == subjectType) {
            throw new ValidationException("Subject Identifier claim subject_type must be present.");
        }
        if (SubjectIdentifierTypes.contains(subjectType) || subjectType.startsWith("x-"))
            return;
        throw new ValidationException("Subject Identifier claim subject_type " +
                "must be defined by specification or begin with x-.");
    }

    public void validate() throws ValidationException {
        validateSubjectType();
    }


    public static class Builder {

        private final SubjectIdentifier claims = new SubjectIdentifier();

        public SubjectIdentifier.Builder issuer(final String iss) {
            final String subjectTypeClaim = claims.get(SUBJECT_TYPE);
            if (SubjectIdentifierTypes.SAML_ASSERTION_ID.equalsName(subjectTypeClaim)) {
                claims.put(SAML_ISSUER, iss);
            }
            else {
                claims.put(ISSUER, iss);
            }
            return this;
        }

        public SubjectIdentifier.Builder subjectType(final String subjectType) {
            claims.put(SUBJECT_TYPE, subjectType);
            return this;
        }

        public SubjectIdentifier.Builder subjectType(final SubjectIdentifierTypes subjectType) {
            claims.put(SUBJECT_TYPE.toString(), subjectType.toString());
            return this;
        }


        public SubjectIdentifier.Builder subject(final String sub) {

            claims.put(SUBJECT, sub);
            return this;
        }

        public SubjectIdentifier.Builder email(final String email) {

            claims.put(EMAIL, email);
            return this;
        }

        public SubjectIdentifier.Builder phoneNumber(final String phoneNumber) {

            claims.put(PHONE, phoneNumber);
            return this;
        }

        public SubjectIdentifier.Builder spagID(final String spag_id) {

            claims.put(SPAG_ID, spag_id);
            return this;
        }

        public SubjectIdentifier.Builder jwtID(final String jwt_id) {

            claims.put(JWT_ID, jwt_id);
            return this;
        }

        public SubjectIdentifier.Builder samlAssertionID(final String assertionId) {

            claims.put(SAML_ASSERTION_ID, assertionId);
            return this;
        }
        public SubjectIdentifier.Builder user(final SubjectIdentifier user) {
            claims.put(USER, user);
            return this;
        }

        public SubjectIdentifier.Builder device(final SubjectIdentifier device) {
            claims.put(DEVICE, device);
            return this;
        }

        public SubjectIdentifier.Builder session(final SubjectIdentifier session) {
            claims.put(SESSION, session);
            return this;
        }


        public SubjectIdentifier.Builder claim(final String name, final Object value) {
            claims.put(name, value);
            return this;
        }

        public SubjectIdentifier build() { return claims; }

    }
}



