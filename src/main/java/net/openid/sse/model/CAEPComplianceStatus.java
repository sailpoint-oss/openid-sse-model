package net.openid.sse.model;

public enum CAEPComplianceStatus {

    COMPLIANT("compliant"),
    NOT_COMPLIANT("not-compliant");

    private final String name;

    CAEPComplianceStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
