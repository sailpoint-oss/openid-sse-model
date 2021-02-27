/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCOptOutInitiated extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutInitiated, RISCOptOutInitiated.Builder> {

        @Override
        protected RISCOptOutInitiated createObj() {
            return new RISCOptOutInitiated();
        }

        protected RISCOptOutInitiated.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_INITIATED);
        }
    }

}
