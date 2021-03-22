/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;

public class RISCSessionsRevoked extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCSessionsRevoked, RISCSessionsRevoked.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_SESSIONS_REVOKED);
        }

        @Override
        protected RISCSessionsRevoked createObj() {
            return new RISCSessionsRevoked();
        }

        protected RISCSessionsRevoked.Builder getThis() {
            return this;
        }
    }

}
