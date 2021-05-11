/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;
import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.ValidationException;

import java.text.ParseException;

public class RISCIdentifierChanged extends SSEvent {

    private static final String NEW_VALUE_MEMBER = "new-value";

    @Override
    public void validate() throws ParseException, SIValidationException, ValidationException {
        super.validate();
        Object oNewValue = getMember(NEW_VALUE_MEMBER);
        if (null == oNewValue) {
            throw new ValidationException(String.format("RISC Identifier Changed event member %s must be present.", NEW_VALUE_MEMBER));
        }
        if (!(oNewValue instanceof String)) {
            throw new ValidationException(String.format("RISC Identifier Changed event member %s must be a String", NEW_VALUE_MEMBER));
        }
    }

    public static class Builder extends SSEvent.Builder<RISCIdentifierChanged, RISCIdentifierChanged.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_IDENTIFIER_CHANGED);
        }

        @Override
        protected RISCIdentifierChanged createObj() {
            return new RISCIdentifierChanged();
        }

        protected RISCIdentifierChanged.Builder getThis() {
            return this;
        }

        public RISCIdentifierChanged.Builder newValue(final String value) {
            members.put(NEW_VALUE_MEMBER, value);
            return thisObj;
        }

    }

}
