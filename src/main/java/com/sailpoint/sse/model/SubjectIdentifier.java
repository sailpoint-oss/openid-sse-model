/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

public class SubjectIdentifier extends JSONObject {

    public final String get(SubjectIdentifierMembers member) {
        final String s = member.toString();
        return (String) this.get(s);
    }

    public void put(final SubjectIdentifierMembers member, Object o) {
        final String s = member.toString();
        this.put(s, o);
    }

    public void validateFormat() throws ValidationException {
        final String format = this.get(SubjectIdentifierMembers.FORMAT);
        if (null == format) {
            return;
        }
        if (IdentifierFormats.contains(format) || format.startsWith("x-"))
            return;
        throw new ValidationException("Subject Identifier member format " +
                "must be defined by specification or begin with x-.");
    }

    public void validate() throws ValidationException {
        validateFormat();

        // Subject Identifiers can be complex (thus recursive)
        for (Entry<String, Object> entry : entrySet()) {
            if (entry.getValue() instanceof SubjectIdentifier) {
                SubjectIdentifier si = (SubjectIdentifier) entry.getValue();
                si.validate();
            }
        }
    }


    public static class Builder {

        private final SubjectIdentifier members = new SubjectIdentifier();

        public Builder format(final IdentifierFormats format) {
            members.put(SubjectIdentifierMembers.FORMAT, format.toString());
            return this;
        }

        public Builder issuer(final String iss) {
            final String subjectTypeMember = members.get(SubjectIdentifierMembers.FORMAT);
            if (IdentifierFormats.SAML_ASSERTION_ID.equalsName(subjectTypeMember)) {
                members.put(SubjectIdentifierMembers.SAML_ISSUER, iss);
            } else {
                members.put(SubjectIdentifierMembers.ISSUER, iss);
            }
            return this;
        }

        public Builder subject(final String sub) {

            members.put(SubjectIdentifierMembers.SUBJECT, sub);
            return this;
        }

        public Builder email(final String email) {

            members.put(SubjectIdentifierMembers.EMAIL, email);
            return this;
        }

        public Builder phoneNumber(final String phoneNumber) {

            members.put(SubjectIdentifierMembers.PHONE, phoneNumber);
            return this;
        }

        public Builder jwtID(final String jwt_id) {

            members.put(SubjectIdentifierMembers.JWT_ID, jwt_id);
            return this;
        }

        public Builder samlAssertionID(final String assertionId) {

            members.put(SubjectIdentifierMembers.SAML_ASSERTION_ID, assertionId);
            return this;
        }

        public Builder id(final String id) {

            members.put(SubjectIdentifierMembers.ID, id);
            return this;
        }

        public Builder user(final SubjectIdentifier user) {
            members.put(SubjectIdentifierMembers.USER, user);
            return this;
        }

        public Builder device(final SubjectIdentifier device) {
            members.put(SubjectIdentifierMembers.DEVICE, device);
            return this;
        }

        public Builder session(final SubjectIdentifier session) {
            members.put(SubjectIdentifierMembers.SESSION, session);
            return this;
        }

        public Builder tenant(final SubjectIdentifier tenant) {
            members.put(SubjectIdentifierMembers.TENANT, tenant);
            return this;
        }


        public Builder member(final String name, final Object value) {
            members.put(name, value);
            return this;
        }

        public SubjectIdentifier build() {
            return members;
        }

    }
}



