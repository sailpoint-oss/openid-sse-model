/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;
import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.Utils;
import com.sailpoint.sse.model.ValidationException;

import java.text.ParseException;

public class RISCAccountDisabled extends SSEvent {

    private static final String REASON_MEMBER = "reason";

    @Override
    public void validate() throws ParseException, SIValidationException, ValidationException {
        super.validate();
        Utils.validateMember(this, REASON_MEMBER, RISCAccountDisabledReasons.class);
    }

    public static class Builder extends SSEvent.Builder<RISCAccountDisabled, RISCAccountDisabled.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_DISABLED);
        }

        @Override
        protected RISCAccountDisabled createObj() {
            return new RISCAccountDisabled();
        }

        protected RISCAccountDisabled.Builder getThis() {
            return this;
        }

        public RISCAccountDisabled.Builder reason(final RISCAccountDisabledReasons reason) {
            members.put(REASON_MEMBER, reason.toString());
            return thisObj;
        }

    }

}
