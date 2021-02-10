package net.openid.sse.model;

public enum SubjectIdentifierTypes {

    // https://github.com/richanna/secevent/blob/master/draft-ietf-secevent-subject-identifiers.txt
    ACCOUNT ("account"),
    EMAIL("email"),
    ISSUER_SUBJECT ("iss-sub"),
    PHONE_NUMBER ("phone_number"),
    ALIASES ( "aliases"),

    // https://bitbucket.org/openid/risc/pull-requests/5 section 3.2
    SPAG( "spag"),
    USER_DEVICE_SESSION ("user-device-session"),
    JWT_ID ("jwt-id"),
    SAML_ASSERTION_ID ("saml-assertion-id");

    private final String name;

    SubjectIdentifierTypes(final String s) {
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
