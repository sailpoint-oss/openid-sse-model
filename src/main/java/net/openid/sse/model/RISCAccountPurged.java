/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCAccountPurged extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCAccountPurged, RISCAccountPurged.Builder> {

        @Override
        protected RISCAccountPurged createObj() {
            return new RISCAccountPurged();
        }

        protected RISCAccountPurged.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_PURGED);
        }
    }

}
