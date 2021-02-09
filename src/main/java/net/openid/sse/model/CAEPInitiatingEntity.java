package net.openid.sse.model;

public enum CAEPInitiatingEntity {
    ADMIN ("admin"),
    USER ("user"),
    POLICY ("policy"),
    SYSTEM ("system");

    private final String name;

    CAEPInitiatingEntity(String s) {
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
