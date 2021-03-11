/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class RISCAccountCredentialChangeRequired extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCAccountCredentialChangeRequired, RISCAccountCredentialChangeRequired.Builder> {

        @Override
        protected RISCAccountCredentialChangeRequired createObj() {
            return new RISCAccountCredentialChangeRequired();
        }

        protected RISCAccountCredentialChangeRequired.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_CREDENTIAL_CHANGE_REQUIRED);
        }
    }

}
