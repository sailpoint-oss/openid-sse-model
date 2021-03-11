/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class CAEPAssuranceLevelChange extends CAEPBaseEvent {

    protected static final String CURRENT_LEVEL = "current_level";
    protected static final String PREVIOUS_LEVEL = "previous_level";
    protected static final String CHANGE_DIRECTION = "change_direction";

    public static class Builder extends CAEPBaseEvent.Builder<CAEPAssuranceLevelChange, CAEPAssuranceLevelChange.Builder> {

        protected CAEPAssuranceLevelChange createObj() {return new CAEPAssuranceLevelChange();}
        protected CAEPAssuranceLevelChange.Builder getThis() { return this; }

        public Builder() {
            super(SSEventTypes.CAEP_ASSURANCE_LEVEL_CHANGE);
        }

        public Builder currentLevel(final NISTAuthenticatorAssuranceLevel level) {
            members.put(CURRENT_LEVEL, level.toString());
            return thisObj;
        }

        public Builder previousLevel(final NISTAuthenticatorAssuranceLevel level) {
            members.put(PREVIOUS_LEVEL, level.toString());
            return thisObj;
        }

        public Builder changeDirection(final AssuranceLevelChangeDirection direction) {
            members.put(CHANGE_DIRECTION, direction.toString());
            return thisObj;
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        Utils.validateMember(this, CURRENT_LEVEL,    NISTAuthenticatorAssuranceLevel.class);
        Utils.validateMember(this, PREVIOUS_LEVEL,   NISTAuthenticatorAssuranceLevel.class);
        Utils.validateMember(this, CHANGE_DIRECTION, AssuranceLevelChangeDirection.class);
    }

}
