/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class RISCOptOutInitiated extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutInitiated, RISCOptOutInitiated.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_INITIATED);
        }

        @Override
        protected RISCOptOutInitiated createObj() {
            return new RISCOptOutInitiated();
        }

        protected RISCOptOutInitiated.Builder getThis() {
            return this;
        }
    }

}
