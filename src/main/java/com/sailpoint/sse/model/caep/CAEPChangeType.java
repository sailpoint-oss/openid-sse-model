/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import java.util.HashMap;
import java.util.Map;

public enum CAEPChangeType {

    CREATE("create"),
    REVOKE("revoke"),
    UPDATE("update"),
    DELETE("delete");

    private static final Map<String, CAEPChangeType> BY_NAME = new HashMap<>();

    static {
        for (CAEPChangeType t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    CAEPChangeType(String s) {
        name = s;
    }

    public static CAEPChangeType valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
