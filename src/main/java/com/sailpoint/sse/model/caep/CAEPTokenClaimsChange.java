/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.ValidationException;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;

public class CAEPTokenClaimsChange extends CAEPBaseEvent {

    private static final String CLAIMS = "claims";

    @Override
    public void validate() throws SIValidationException, ValidationException {
        super.validate();
        JSONObject members = getEventMembers();
        final Object o = members.get(CLAIMS);
        if (null == o) {
            throw new ValidationException(this.getClass().getName() + " member " + CLAIMS + " is missing or null.");
        }
    }

    public static class Builder extends CAEPBaseEvent.Builder<CAEPTokenClaimsChange, CAEPTokenClaimsChange.Builder> {

        public Builder() {
            super(SSEventTypes.CAEP_TOKEN_CLAIMS_CHANGE);
        }

        protected CAEPTokenClaimsChange createObj() {
            return new CAEPTokenClaimsChange();
        }

        protected CAEPTokenClaimsChange.Builder getThis() {
            return this;
        }

        public CAEPTokenClaimsChange.Builder claims(final JSONObject newClaims) {
            members.put(CLAIMS, newClaims);
            return thisObj;
        }


    }


}
