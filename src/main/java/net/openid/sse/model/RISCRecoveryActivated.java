/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCRecoveryActivated extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCRecoveryActivated, RISCRecoveryActivated.Builder> {

        @Override
        protected RISCRecoveryActivated createObj() {
            return new RISCRecoveryActivated();
        }

        protected RISCRecoveryActivated.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_RECOVERY_ACTIVATED);
        }
    }

}
