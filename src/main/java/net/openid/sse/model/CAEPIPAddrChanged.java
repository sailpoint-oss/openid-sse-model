/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class CAEPIPAddrChanged extends CAEPBaseEvent {
    private static final String IPADDRESS_MEMBER   = "ip_address";

    public static class Builder extends CAEPBaseEvent.Builder<CAEPIPAddrChanged, CAEPIPAddrChanged.Builder> {


        protected CAEPIPAddrChanged createObj() {return new CAEPIPAddrChanged();}
        protected CAEPIPAddrChanged.Builder getThis() { return this; }

        public Builder() {
            super(SSEventTypes.CAEP_IPADDR_CHANGED);
        }

        public Builder ipAddress(final String ipAddress) {
            members.put(IPADDRESS_MEMBER, ipAddress);
            return thisObj;
        }
    }



}
