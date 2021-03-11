/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class RISCAccountEnabled extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCAccountEnabled, RISCAccountEnabled.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_ENABLED);
        }

        @Override
        protected RISCAccountEnabled createObj() {
            return new RISCAccountEnabled();
        }

        protected RISCAccountEnabled.Builder getThis() {
            return this;
        }
    }

}
