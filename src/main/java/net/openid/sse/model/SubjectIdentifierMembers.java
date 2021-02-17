package net.openid.sse.model;

import java.util.HashMap;
import java.util.Map;

public enum SubjectIdentifierMembers {

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

    SubjectIdentifierMembers(final String s) {
        name = s;
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

    private static final Map<String, SubjectIdentifierMembers> BY_NAME = new HashMap<>();

    static {
        for (SubjectIdentifierMembers t: values()) {
            BY_NAME.put(t.name, t);
        }
    }

    public static SubjectIdentifierMembers valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

}
