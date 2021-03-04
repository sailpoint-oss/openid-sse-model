/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class CAEPSessionRevoked extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder<CAEPSessionRevoked, CAEPSessionRevoked.Builder> {

        protected CAEPSessionRevoked createObj() {return new CAEPSessionRevoked();}
        protected CAEPSessionRevoked.Builder getThis() { return this; }


        public Builder() {
            super(SSEventTypes.CAEP_SESSION_REVOKED);
        }
    }
}
