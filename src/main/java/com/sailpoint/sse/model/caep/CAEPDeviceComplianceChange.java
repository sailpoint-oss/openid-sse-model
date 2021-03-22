/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.Utils;
import com.sailpoint.sse.model.ValidationException;

public class CAEPDeviceComplianceChange extends CAEPBaseEvent {

    private static final String PREVIOUS_STATUS = "previous_status";
    private static final String CURRENT_STATUS = "current_status";

    @Override
    public void validate() throws ValidationException {
        super.validate();
        Utils.validateMember(this, PREVIOUS_STATUS, CAEPComplianceStatus.class);
        Utils.validateMember(this, CURRENT_STATUS, CAEPComplianceStatus.class);
    }

    public static class Builder extends CAEPBaseEvent.Builder<CAEPDeviceComplianceChange, CAEPDeviceComplianceChange.Builder> {

        public Builder() {
            super(SSEventTypes.CAEP_DEVICE_COMPLIANCE_CHANGE);
        }

        protected CAEPDeviceComplianceChange createObj() {
            return new CAEPDeviceComplianceChange();
        }

        protected CAEPDeviceComplianceChange.Builder getThis() {
            return this;
        }

        public Builder previousStatus(final CAEPComplianceStatus status) {
            members.put(PREVIOUS_STATUS, status.toString());
            return thisObj;
        }

        public Builder currentStatus(final CAEPComplianceStatus status) {
            members.put(CURRENT_STATUS, status.toString());
            return thisObj;
        }

    }

}

