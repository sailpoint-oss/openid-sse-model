/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCOptIn extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptIn, RISCOptIn.Builder> {

        @Override
        protected RISCOptIn createObj() {
            return new RISCOptIn();
        }

        protected RISCOptIn.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_OPT_IN);
        }
    }

}
