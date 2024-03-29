/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import java.util.HashMap;
import java.util.Map;

public enum NISTAuthenticatorAssuranceLevel {

    NIST_AAL1("nist-aal1"),
    NIST_AAL2("nist-aal2"),
    NIST_AAL3("nist-aal3");

    private static final Map<String, NISTAuthenticatorAssuranceLevel> BY_NAME = new HashMap<>();

    static {
        for (NISTAuthenticatorAssuranceLevel t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    NISTAuthenticatorAssuranceLevel(final String s) {
        name = s;
    }

    public static NISTAuthenticatorAssuranceLevel valueOfLabel(final String name) {
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
