/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.did;

import com.sailpoint.sse.model.SubjectIdentifier;
import com.sailpoint.sse.model.SubjectIdentifierFormats;
import com.sailpoint.sse.model.SubjectIdentifierMembers;
import com.sailpoint.sse.model.ValidationException;

import static com.sailpoint.sse.model.SubjectIdentifierMembers.FORMAT;
import static com.sailpoint.sse.model.SubjectIdentifierMembers.URI;

public class DIDSubjectIdentifier extends SubjectIdentifier {



    private static void validateUri(final DIDSubjectIdentifier subj) throws ValidationException {
        final Object o = subj.get(URI.toString());
        if (null == o) {
            throw new ValidationException(subj.getClass().getName() + " member uri is missing or null.");
        }
        if (o instanceof String){
            final String value = (String) o;
            if (value.isEmpty()) {
                throw new ValidationException(subj.getClass().getName() + " member uri is empty.");
            }
        }
        else{
            throw new ValidationException(subj.getClass().getName() + " member uri is not a String.");
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        final String format = (String) get(FORMAT.toString());
        if (!format.equals(SubjectIdentifierFormats.DID.toString())) {
            throw new ValidationException("DID Subject Identifiers must have format did.");
        }
        validateUri(this);
    }

    public static class Builder {

        private final DIDSubjectIdentifier members = new DIDSubjectIdentifier();

        // Redefining format is easier than genericizing SubjectIdentifier
        public Builder format(final SubjectIdentifierFormats format) {
            members.put(SubjectIdentifierMembers.FORMAT, format.toString());
            return this;
        }

        public Builder uri(final String uri) {
            members.put(SubjectIdentifierMembers.URI.toString(), uri);
            return this;
        }

        public DIDSubjectIdentifier build() {
            return members;
        }
    }
}



