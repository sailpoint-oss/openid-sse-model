/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;

public class RISCOptOutEffective extends SSEvent {

    public static class Builder extends SSEvent.Builder<RISCOptOutEffective, RISCOptOutEffective.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_OPT_OUT_EFFECTIVE);
        }

        @Override
        protected RISCOptOutEffective createObj() {
            return new RISCOptOutEffective();
        }

        protected RISCOptOutEffective.Builder getThis() {
            return this;
        }
    }

}
