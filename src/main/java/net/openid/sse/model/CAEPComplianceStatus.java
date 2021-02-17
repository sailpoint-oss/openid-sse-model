package net.openid.sse.model;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, CAEPComplianceStatus> BY_NAME = new HashMap<>();

    static {
        for (CAEPComplianceStatus t: values()) {
            BY_NAME.put(t.name, t);
        }
    }

    public static CAEPComplianceStatus valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

}
