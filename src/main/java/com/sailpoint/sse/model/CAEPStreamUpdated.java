/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import static com.sailpoint.sse.model.SubjectIdentifierMembers.SUBJECT_TYPE;

public class CAEPStreamUpdated extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder<CAEPStreamUpdated, CAEPStreamUpdated.Builder> {

        protected CAEPStreamUpdated createObj() {return new CAEPStreamUpdated();}
        protected CAEPStreamUpdated.Builder getThis() { return this; }


        public Builder() {
            super(SSEventTypes.CAEP_STREAM_UPDATED);
        }
    }

    /*
       The "stream-updated" event contains a "subject" claim of the subject
       type "spag".  It also contains a "properties" claim, which is a JSON
       object containing the following fields:
       status     REQUIRED.  Defines the new status of the stream for the Subject
      Identifier specified in the Subject.

      However, I believe this is incorrect.  The status member is in SSEvent, there's no need for a properties
      member with a status sub-member here.  Just make sure status is present.  Comment filed on the caep PR.

     */
    @Override
    public void validate() throws ValidationException {
        super.validate();
        SubjectIdentifier subj = getSubjectIdentifier();
        final String subjectType = subj.get(SUBJECT_TYPE);
        if (null == subjectType || !subjectType.equals(SubjectIdentifierTypes.SPAG.toString())) {
            throw new ValidationException("CAEP Stream Updated events must have a subject identifier with subject_type=spag");
        }

        final String status = getStatus();
        if (null == status) {
            throw new ValidationException("CAEP Stream Updated events must have a status member");
        }
    }
}
