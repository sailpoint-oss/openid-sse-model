/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import java.util.HashMap;
import java.util.Map;

public enum RISCAccountDisabledReasons {

    HIJACKING("hijacking"),
    BULK_ACCOUNT("bulk-account");

    private static final Map<String, RISCAccountDisabledReasons> BY_NAME = new HashMap<>();

    static {
        for (RISCAccountDisabledReasons t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    RISCAccountDisabledReasons(final String s) {
        name = s;
    }

    public static RISCAccountDisabledReasons valueOfLabel(final String name) {
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
