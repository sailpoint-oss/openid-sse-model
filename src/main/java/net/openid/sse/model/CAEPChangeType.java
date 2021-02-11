package net.openid.sse.model;

public enum CAEPChangeType {

    CREATE("create"),
    REVOKE("revoke"),
    UPDATE("update"),
    DELETE("delete");

    private final String name;

    CAEPChangeType(String s) {
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
