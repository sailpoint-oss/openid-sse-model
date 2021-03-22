/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;

public class RISCOptOutCancelled extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutCancelled, RISCOptOutCancelled.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_CANCELLED);
        }

        @Override
        protected RISCOptOutCancelled createObj() {
            return new RISCOptOutCancelled();
        }

        protected RISCOptOutCancelled.Builder getThis() {
            return this;
        }
    }

}
