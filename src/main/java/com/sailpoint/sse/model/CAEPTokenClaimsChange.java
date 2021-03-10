/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public class CAEPTokenClaimsChange extends CAEPBaseEvent {

    private static final String CLAIMS = "claims";

    public static class Builder extends CAEPBaseEvent.Builder<CAEPTokenClaimsChange, CAEPTokenClaimsChange.Builder> {

        protected CAEPTokenClaimsChange createObj() {return new CAEPTokenClaimsChange();}
        protected CAEPTokenClaimsChange.Builder getThis() { return this; }

        public Builder() {
            super(SSEventTypes.CAEP_TOKEN_CLAIMS_CHANGE);
        }

        public CAEPTokenClaimsChange.Builder claims(final JSONObject newClaims) {
            members.put(CLAIMS, newClaims);
            return thisObj;
        }


    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        JSONObject members = getEventMembers();
        final Object o = members.get(CLAIMS);
        if (null == o) {
            throw new ValidationException(this.getClass().getName() + " member " + CLAIMS + " is missing or null.");
        }
    }


}
