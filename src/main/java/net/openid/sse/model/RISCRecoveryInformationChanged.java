/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCRecoveryInformationChanged extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCRecoveryInformationChanged, RISCRecoveryInformationChanged.Builder> {

        @Override
        protected RISCRecoveryInformationChanged createObj() {
            return new RISCRecoveryInformationChanged();
        }

        protected RISCRecoveryInformationChanged.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_RECOVERY_INFORMATION_CHANGED);
        }
    }

}
