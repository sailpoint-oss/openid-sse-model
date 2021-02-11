package net.openid.sse.model;

import java.util.HashMap;
import java.util.Map;

public enum SubjectIdentifierTypes {

    // https://github.com/richanna/secevent/blob/master/draft-ietf-secevent-subject-identifiers.txt
    ACCOUNT ("account"),
    EMAIL("email"),
    ISSUER_SUBJECT ("iss_sub"),
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

    private static final Map<String, SubjectIdentifierTypes> BY_NAME = new HashMap<>();

    static {
        for (SubjectIdentifierTypes t: values()) {
            BY_NAME.put(t.name, t);
        }
    }

    public static SubjectIdentifierTypes valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
