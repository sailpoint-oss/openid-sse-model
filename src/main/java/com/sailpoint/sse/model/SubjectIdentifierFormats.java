/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.sailpoint.sse.model.did.DIDSubjectIdentifier;

import java.util.HashMap;
import java.util.Map;

public enum SubjectIdentifierFormats {

    // https://github.com/richanna/secevent/blob/master/draft-ietf-secevent-subject-identifiers.txt
    ACCOUNT("account", AccountSubjectIdentifier.class),
    EMAIL("email"),
    PHONE_NUMBER("phone_number"),
    ISSUER_SUBJECT("iss_sub", IssSubSubjectIdentifier.class),
    ALIASES("aliases"),

    JWT_ID("jwt_id"),
    SAML_ASSERTION_ID("saml_assertion_id"),

    //  https://bitbucket.org/openid/risc/pull-requests/8/align-with-new-subject-identifier-draft
    OPAQUE("opaque"),

    // https://bitbucket.org/openid/risc/src/master/oauth-event-types-1_0.txt
    OAUTH_TOKEN("oauth_token"),

    // https://github.com/richanna/secevent/pull/2
    // Decentralized Identifier
    DID("did", DIDSubjectIdentifier.class);

    private static final Map<String, SubjectIdentifierFormats> BY_NAME = new HashMap<>();

    static {
        for (SubjectIdentifierFormats t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;
    private final Class<? extends SubjectIdentifier> cls;

    SubjectIdentifierFormats(final String s) {
        name = s;
        this.cls = null;
    }

    SubjectIdentifierFormats(final String s, final Class<? extends SubjectIdentifier> cls) {
        name = s;
        this.cls = cls;
    }

    public static SubjectIdentifierFormats enumByName(String name) { return BY_NAME.get(name); }

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

    public Class<? extends SubjectIdentifier> getCls() { return this.cls; }
}
