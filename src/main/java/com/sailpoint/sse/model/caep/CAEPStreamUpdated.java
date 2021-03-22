/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import com.sailpoint.sse.model.SSEventTypes;

public class CAEPStreamUpdated extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder<CAEPStreamUpdated, CAEPStreamUpdated.Builder> {

        public Builder() {
            super(SSEventTypes.SSE_STREAM_UPDATED);
        }

        protected CAEPStreamUpdated createObj() {
            return new CAEPStreamUpdated();
        }

        protected CAEPStreamUpdated.Builder getThis() {
            return this;
        }
    }
}
