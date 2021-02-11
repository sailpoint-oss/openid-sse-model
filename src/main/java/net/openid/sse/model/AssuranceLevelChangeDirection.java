package net.openid.sse.model;

public enum AssuranceLevelChangeDirection {

    INCREASE("increase"),
    DECREASE("decrease");

    private final String name;

    AssuranceLevelChangeDirection(final String s) {
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
