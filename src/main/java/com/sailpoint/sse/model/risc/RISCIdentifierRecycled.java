/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.risc;

import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifierFormats;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifierMembers;
import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.ValidationException;

import java.text.ParseException;

public class RISCIdentifierRecycled extends SSEvent {

    /*
     *  The subject type MUST be either "email" or "phone".
     */
    @Override
    public void validate() throws ParseException, SIValidationException, ValidationException {
        super.validate();
        SubjectIdentifier subj = getSubjectIdentifier();
        final String type = subj.getString(SubjectIdentifierMembers.FORMAT);
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
