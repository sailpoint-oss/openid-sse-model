/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCSessionsRevoked extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCSessionsRevoked, RISCSessionsRevoked.Builder> {

        @Override
        protected RISCSessionsRevoked createObj() {
            return new RISCSessionsRevoked();
        }

        protected RISCSessionsRevoked.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_SESSIONS_REVOKED);
        }
    }

}
