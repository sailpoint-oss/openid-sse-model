/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import java.util.HashMap;
import java.util.Map;

public enum CAEPCredentialType {

    PASSWORD("password"),
    PIN("pin"),
    X509("x509"),
    FIDO2_PLATFORM("fido2-platform"),
    FIDO2_ROAMING("fido2-roaming"),
    FIDO2_U2F("fido-u2f"),
    VERIFIABLE_CREDENTIAL("verifiable-credential"),
    PHONE_VOICE("phone-voice"),
    PHONE_SMS("phone-sms"),
    APP("app");

    private static final Map<String, CAEPCredentialType> BY_NAME = new HashMap<>();

    static {
        for (CAEPCredentialType t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    CAEPCredentialType(String s) {
        name = s;
    }

    public static CAEPCredentialType valueOfLabel(String name) {
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
