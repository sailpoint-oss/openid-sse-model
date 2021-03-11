/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import java.util.HashMap;
import java.util.Map;

public enum IdentifierFormats {

    // https://github.com/richanna/secevent/blob/master/draft-ietf-secevent-subject-identifiers.txt
    ACCOUNT ("account"),
    EMAIL("email"),
    PHONE_NUMBER ("phone_number"),
    ISSUER_SUBJECT ("iss_sub"),
    ALIASES ( "aliases"),

    JWT_ID ("jwt_id"),
    SAML_ASSERTION_ID ("saml_assertion_id"),

    //  https://bitbucket.org/openid/risc/pull-requests/8/align-with-new-subject-identifier-draft
    OPAQUE ("opaque");

    private final String name;

    IdentifierFormats(final String s) {
        name = s;
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    private static final Map<String, IdentifierFormats> BY_NAME = new HashMap<>();

    static {
        for (IdentifierFormats t: values()) {
            BY_NAME.put(t.name, t);
        }
    }

    public static IdentifierFormats valueOfLabel(String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
