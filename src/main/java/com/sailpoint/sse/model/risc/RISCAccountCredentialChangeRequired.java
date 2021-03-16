/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;

public class RISCAccountCredentialChangeRequired extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCAccountCredentialChangeRequired, RISCAccountCredentialChangeRequired.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_CREDENTIAL_CHANGE_REQUIRED);
        }

        @Override
        protected RISCAccountCredentialChangeRequired createObj() {
            return new RISCAccountCredentialChangeRequired();
        }

        protected RISCAccountCredentialChangeRequired.Builder getThis() {
            return this;
        }
    }

}
