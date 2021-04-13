/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.oauth;

import java.util.HashMap;
import java.util.Map;

public enum OAuthTokenIdentifierAlg {

    PLAIN("plain"),
    PREFIX("prefix"),
    HASH_SHA512("hash_sha512");

    private static final Map<String, OAuthTokenIdentifierAlg> BY_NAME = new HashMap<>();

    static {
        for (OAuthTokenIdentifierAlg t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    OAuthTokenIdentifierAlg(final String s) {
        name = s;
    }

    public static OAuthTokenIdentifierAlg valueOfLabel(final String name) {
        return BY_NAME.get(name);
    }

    @SuppressWarnings("unused")
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
