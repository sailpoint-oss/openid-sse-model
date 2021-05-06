/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;

public class NonstandardSSEvent extends SSEvent {

    public NonstandardSSEvent(final String eventName) {
        super();
        setEventTypeName(eventName);
    }
    public NonstandardSSEvent() {
        super();
    }

    @Override
    public void validate() throws SIValidationException, ValidationException {
        validateSubject();
    }

    public static class Builder extends SSEvent.Builder<NonstandardSSEvent, NonstandardSSEvent.Builder> {

        private String eventName;

        public Builder() { super(); }

        @Override
        protected NonstandardSSEvent createObj() {
            return new NonstandardSSEvent();
        }

        protected Builder getThis() {
            return this;
        }

        public Builder eventName(final String name) {
            eventName = name;
            return this;
        }

        @Override
        public NonstandardSSEvent build() {
            NonstandardSSEvent t = createObj();
            t.setEventTypeName(eventName);
            t.put(t.getEventTypeName(), members);
            return t;
        }
    }
}
