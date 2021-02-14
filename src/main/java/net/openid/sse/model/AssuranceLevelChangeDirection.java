package net.openid.sse.model;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, AssuranceLevelChangeDirection> BY_NAME = new HashMap<>();

    static {
        for (AssuranceLevelChangeDirection t: values()) {
            BY_NAME.put(t.name, t);
        }
    }

    public static AssuranceLevelChangeDirection valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

}
