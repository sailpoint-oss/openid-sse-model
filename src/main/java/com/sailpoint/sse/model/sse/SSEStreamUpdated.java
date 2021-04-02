/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.sse;

import com.sailpoint.sse.model.*;

public class SSEStreamUpdated extends SSEvent {

    private static final String STATUS_MEMBER = "status";

    public static class Builder extends SSEvent.Builder<SSEStreamUpdated, SSEStreamUpdated.Builder> {

        public Builder() {
            super(SSEventTypes.SSE_STREAM_UPDATED);
        }

        @Override
        protected SSEStreamUpdated createObj() {
            return new SSEStreamUpdated();
        }

        protected SSEStreamUpdated.Builder getThis() {
            return this;
        }

        public Builder status(final StreamStatus status) {
            members.put(STATUS_MEMBER, status.toString());
            return thisObj;
        }

    }

    @Override
    public void validate() throws ValidationException {
        super.validateEventTypeName();
        Utils.validateMember(this, STATUS_MEMBER, StreamStatus.class);
    }

}
