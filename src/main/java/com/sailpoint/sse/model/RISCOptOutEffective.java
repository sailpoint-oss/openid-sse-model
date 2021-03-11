/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class RISCOptOutEffective extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutEffective, RISCOptOutEffective.Builder> {

        @Override
        protected RISCOptOutEffective createObj() {
            return new RISCOptOutEffective();
        }

        protected RISCOptOutEffective.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_EFFECTIVE);
        }
    }

}