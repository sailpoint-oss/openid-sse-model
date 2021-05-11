/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.Utils;
import com.sailpoint.sse.model.ValidationException;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;

import java.text.ParseException;

public class CAEPAssuranceLevelChange extends CAEPBaseEvent {

    private static final String CURRENT_LEVEL = "current_level";
    private static final String PREVIOUS_LEVEL = "previous_level";
    private static final String CHANGE_DIRECTION = "change_direction";

    @Override
    public void validate() throws ParseException, SIValidationException, ValidationException {
        super.validate();
        Utils.validateMember(this, CURRENT_LEVEL, NISTAuthenticatorAssuranceLevel.class);
        Utils.validateMember(this, PREVIOUS_LEVEL, NISTAuthenticatorAssuranceLevel.class);
        Utils.validateMember(this, CHANGE_DIRECTION, CAEPAssuranceLevelChangeDirection.class);
    }

    public static class Builder extends CAEPBaseEvent.Builder<CAEPAssuranceLevelChange, CAEPAssuranceLevelChange.Builder> {

        public Builder() {
            super(SSEventTypes.CAEP_ASSURANCE_LEVEL_CHANGE);
        }

        protected CAEPAssuranceLevelChange createObj() {
            return new CAEPAssuranceLevelChange();
        }

        protected CAEPAssuranceLevelChange.Builder getThis() {
            return this;
        }

        public Builder currentLevel(final NISTAuthenticatorAssuranceLevel level) {
            members.put(CURRENT_LEVEL, level.toString());
            return thisObj;
        }

        public Builder previousLevel(final NISTAuthenticatorAssuranceLevel level) {
            members.put(PREVIOUS_LEVEL, level.toString());
            return thisObj;
        }

        public Builder changeDirection(final CAEPAssuranceLevelChangeDirection direction) {
            members.put(CHANGE_DIRECTION, direction.toString());
            return thisObj;
        }
    }

}
