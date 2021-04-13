/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.oauth;

import java.util.HashMap;
import java.util.Map;

public enum OAuthTokenType {

    ACCESS_TOKEN("access_token"),
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token");

    private static final Map<String, OAuthTokenType> BY_NAME = new HashMap<>();

    static {
        for (OAuthTokenType t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    OAuthTokenType(final String s) {
        name = s;
    }

    public static OAuthTokenType valueOfLabel(final String name) {
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
