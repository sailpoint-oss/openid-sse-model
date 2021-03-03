/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class RISCAccountDisabled extends SSEvent {

    private static final String REASON_MEMBER = "reason";

    public static class Builder extends SSEvent.Builder<RISCAccountDisabled, RISCAccountDisabled.Builder> {

        @Override
        protected RISCAccountDisabled createObj() {
            return new RISCAccountDisabled();
        }

        protected RISCAccountDisabled.Builder getThis() {
            return this;
        }

        public Builder() {
            super(SSEventTypes.RISC_ACCOUNT_DISABLED);
        }

        public RISCAccountDisabled.Builder reason(final RISCAccountDisabledReasons reason) {
            members.put(REASON_MEMBER, reason.toString());
            return thisObj;
        }

    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        Utils.validateMember(this, REASON_MEMBER, RISCAccountDisabledReasons.class);
    }

}
