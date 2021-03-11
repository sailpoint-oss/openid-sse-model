/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import java.util.HashMap;
import java.util.Map;

public enum CAEPInitiatingEntity {
    ADMIN("admin"),
    USER("user"),
    POLICY("policy"),
    SYSTEM("system");

    private static final Map<String, CAEPInitiatingEntity> BY_NAME = new HashMap<>();

    static {
        for (CAEPInitiatingEntity t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    CAEPInitiatingEntity(String s) {
        name = s;
    }

    public static CAEPInitiatingEntity valueOfLabel(String name) {
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
