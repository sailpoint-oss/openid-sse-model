/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import java.util.HashMap;
import java.util.Map;

public enum CAEPComplianceStatus {

    COMPLIANT("compliant"),
    NOT_COMPLIANT("not-compliant");

    private static final Map<String, CAEPComplianceStatus> BY_NAME = new HashMap<>();

    static {
        for (CAEPComplianceStatus t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    CAEPComplianceStatus(final String s) {
        name = s;
    }

    public static CAEPComplianceStatus valueOfLabel(final String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
