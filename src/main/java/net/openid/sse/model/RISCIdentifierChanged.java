/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCIdentifierChanged extends SSEvent {

    private static final String NEW_VALUE_MEMBER = "new-value";

    public static class Builder extends SSEvent.Builder<RISCIdentifierChanged, RISCIdentifierChanged.Builder> {

        @Override
        protected RISCIdentifierChanged createObj() {
            return new RISCIdentifierChanged();
        }

        protected RISCIdentifierChanged.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_IDENTIFIER_CHANGED);
        }

        public RISCIdentifierChanged.Builder newValue(final String value) {
            members.put(NEW_VALUE_MEMBER, value);
            return thisObj;
        }

    }

}
