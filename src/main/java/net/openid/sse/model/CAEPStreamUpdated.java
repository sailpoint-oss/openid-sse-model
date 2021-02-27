/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class CAEPStreamUpdated extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder<CAEPStreamUpdated, CAEPStreamUpdated.Builder> {

        protected CAEPStreamUpdated createObj() {return new CAEPStreamUpdated();}
        protected CAEPStreamUpdated.Builder getThis() { return this; }


        public Builder() {
            super(SSEventTypes.CAEP_STREAM_UPDATED);
        }
    }
}
