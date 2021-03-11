/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import java.util.HashMap;
import java.util.Map;

public enum SubjectIdentifierMembers {

    FORMAT("format"),
    ISSUER("iss"),
    SUBJECT("sub"),
    EMAIL("email"),
    PHONE("phone_number"),
    JWT_ID("jti"),
    SAML_ISSUER("issuer"),
    SAML_ASSERTION_ID("assertion_id"),
    USER("user"),
    DEVICE("device"),
    SESSION("session"),
    APPLICATION("application"),
    TENANT("tenant"),
    ORG_UNIT("org-unit"),
    GROUP("group"),
    ID("id");

    private static final Map<String, SubjectIdentifierMembers> BY_NAME = new HashMap<>();

    static {
        for (SubjectIdentifierMembers t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    SubjectIdentifierMembers(final String s) {
        name = s;
    }

    public static SubjectIdentifierMembers valueOfLabel(String name) {
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
