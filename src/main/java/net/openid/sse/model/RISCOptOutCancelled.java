/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCOptOutCancelled extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutCancelled, RISCOptOutCancelled.Builder> {

        @Override
        protected RISCOptOutCancelled createObj() {
            return new RISCOptOutCancelled();
        }

        protected RISCOptOutCancelled.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_CANCELLED);
        }
    }

}
