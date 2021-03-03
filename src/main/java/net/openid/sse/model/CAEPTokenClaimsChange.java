/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class CAEPTokenClaimsChange extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder<CAEPTokenClaimsChange, CAEPTokenClaimsChange.Builder> {

        protected CAEPTokenClaimsChange createObj() {return new CAEPTokenClaimsChange();}
        protected CAEPTokenClaimsChange.Builder getThis() { return this; }

        public Builder() {
            super(SSEventTypes.CAEP_TOKEN_CLAIMS_CHANGE);
        }

    }
}
