/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.sailpoint.sse.model.caep.*;
import com.sailpoint.sse.model.risc.*;
import com.sailpoint.sse.model.sse.*;

import java.util.HashMap;
import java.util.Map;


public enum SSEventTypes {

    RISC_ACCOUNT_CREDENTIAL_CHANGE_REQUIRED("account-credential-change-required", SSESpecs.RISC_PREFIX, RISCAccountCredentialChangeRequired.class),
    RISC_ACCOUNT_PURGED("account-purged", SSESpecs.RISC_PREFIX, RISCAccountPurged.class),
    RISC_ACCOUNT_DISABLED("account-disabled", SSESpecs.RISC_PREFIX, RISCAccountDisabled.class),
    RISC_ACCOUNT_ENABLED("account-enabled", SSESpecs.RISC_PREFIX, RISCAccountEnabled.class),
    RISC_IDENTIFIER_CHANGED("identifier-changed", SSESpecs.RISC_PREFIX, RISCIdentifierChanged.class),
    RISC_IDENTIFIER_RECYCLED("identifier-recycled", SSESpecs.RISC_PREFIX, RISCIdentifierRecycled.class),
    RISC_OPT_IN("opt-in", SSESpecs.RISC_PREFIX, RISCOptIn.class),
    RISC_OPT_OUT_INITIATED("opt-out-initiated", SSESpecs.RISC_PREFIX, RISCOptOutInitiated.class),
    RISC_OPT_OUT_CANCELLED("opt-out-cancelled", SSESpecs.RISC_PREFIX, RISCOptOutCancelled.class),
    RISC_OPT_OUT_EFFECTIVE("opt-out-effective", SSESpecs.RISC_PREFIX, RISCOptOutEffective.class),
    RISC_RECOVERY_ACTIVATED("recovery-activated", SSESpecs.RISC_PREFIX, RISCRecoveryActivated.class),
    RISC_RECOVERY_INFORMATION_CHANGED("recovery-information-changed", SSESpecs.RISC_PREFIX, RISCRecoveryInformationChanged.class),
    RISC_SESSIONS_REVOKED("sessions-revoked", SSESpecs.RISC_PREFIX, RISCSessionsRevoked.class),

    CAEP_IPADDR_CHANGED("ip-address-changed", SSESpecs.CAEP_PREFIX, CAEPIPAddrChanged.class),
    //Where is this defined?
    //CAEP_TOKEN_REVOCATION("token-revocation", SSESpecs.CAEP_PREFIX.class),
    CAEP_SESSION_REVOKED("session-revoked", SSESpecs.CAEP_PREFIX, CAEPSessionRevoked.class),
    CAEP_TOKEN_CLAIMS_CHANGE("token-claims-change", SSESpecs.CAEP_PREFIX, CAEPTokenClaimsChange.class),
    CAEP_CREDENTIAL_CHANGE("credential-change", SSESpecs.CAEP_PREFIX, CAEPCredentialChange.class),
    CAEP_ASSURANCE_LEVEL_CHANGE("assurance-level-change", SSESpecs.CAEP_PREFIX, CAEPAssuranceLevelChange.class),
    CAEP_DEVICE_COMPLIANCE_CHANGE("device-compliance-change", SSESpecs.CAEP_PREFIX, CAEPDeviceComplianceChange.class),

    SSE_VERIFICATION("verification", SSESpecs.SSE_PREFIX, SSEVerification.class),
    SSE_STREAM_UPDATED("stream-updated", SSESpecs.SSE_PREFIX, SSEStreamUpdated.class);

    // Name space prefix string for Shared Signals and Event types.
    private static final String SSE_URL_PREFIX = "https://schemas.openid.net/secevent/";
    private static final Map<String, SSEventTypes> BY_NAME = new HashMap<>();

    static {
        for (SSEventTypes t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;
    private final Class<? extends SSEvent> cls;

    SSEventTypes(final String s, final String spec, final Class<? extends SSEvent> cls) {
        name = SSE_URL_PREFIX + spec + "/event-type/" + s;
        this.cls = cls;
    }

    public static SSEventTypes enumByName(String name) {
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

    public Class<? extends SSEvent> getCls() { return this.cls; }

}

/**
 * Commonly used SSE event types for SSE/CAEP events.
 */
class SSESpecs {
    public static final String RISC_PREFIX = "risc";
    public static final String SSE_PREFIX = "sse";
    public static final String CAEP_PREFIX = "caep";

    private SSESpecs() {
    }
}
