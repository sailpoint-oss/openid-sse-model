/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.sse.model.*;

public class RISCIdentifierRecycled extends SSEvent {

    /*
     *  The subject type MUST be either "email" or "phone".
     */
    @Override
    public void validate() throws ValidationException {
        super.validate();
        SubjectIdentifier subj = getSubjectIdentifier();
        final String type = subj.get(SubjectIdentifierMembers.FORMAT);
        if (SubjectIdentifierFormats.EMAIL.equalsName(type) || SubjectIdentifierFormats.PHONE_NUMBER.equalsName(type)) {
            return;
        }
        throw new ValidationException("RISC Identifier Recycled event must have a subject_type of email or phone_number");
    }

    public static class Builder extends SSEvent.Builder<RISCIdentifierRecycled, RISCIdentifierRecycled.Builder> {

        public Builder() {
            super(SSEventTypes.RISC_IDENTIFIER_RECYCLED);
        }

        @Override
        protected RISCIdentifierRecycled createObj() {
            return new RISCIdentifierRecycled();
        }

        protected RISCIdentifierRecycled.Builder getThis() {
            return this;
        }
    }

}
