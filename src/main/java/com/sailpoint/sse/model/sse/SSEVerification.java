/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.sse;

import com.sailpoint.sse.model.*;

public class SSEVerification extends SSEvent {

    private static final String STATE_MEMBER = "state";

    @Override
    public void validate() throws ValidationException {
        super.validateEventTypeName();
    }



    public static class Builder extends SSEvent.Builder<SSEVerification, SSEVerification.Builder> {

        public Builder() {
            super(SSEventTypes.SSE_VERIFICATION);
        }

        @Override
        protected SSEVerification createObj() {
            return new SSEVerification();
        }

        protected SSEVerification.Builder getThis() {
            return this;
        }

        public Builder state(final String state) {
            super.members.put(STATE_MEMBER, state);
            return thisObj;
        }
    }

}
