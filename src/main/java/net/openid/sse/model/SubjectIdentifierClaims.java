package net.openid.sse.model;

public enum SubjectIdentifierClaims {

    SUBJECT_TYPE("subject_type"),
    ISSUER("iss"),
    SUBJECT("sub"),
    EMAIL("email"),
    PHONE("phone_number"),
    JWT_ID("jti"),
    SAML_ISSUER("issuer"),
    SAML_ASSERTION_ID("assertion_id"),
    USER("user"),
    DEVICE("device"),
    SESSION("session"),
    SPAG_ID("spag_id");

    private final String name;

    SubjectIdentifierClaims(final String s) {
        name = s;
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
