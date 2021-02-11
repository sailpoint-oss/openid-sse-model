package net.openid.sse.model;

public enum NISTAuthenticatorAssuranceLevel {

    NIST_AAL1("nist-aal1"),
    NIST_AAL2("nist-aal2"),
    NIST_AAL3("nist-aal3");

    private final String name;

    NISTAuthenticatorAssuranceLevel(final String s) {
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
