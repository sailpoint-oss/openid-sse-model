/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import static net.openid.sse.model.SubjectIdentifierMembers.*;

public class SubjectIdentifier extends JSONObject {

    public final String get(SubjectIdentifierMembers member) {
        final String s = member.toString();
        return (String) this.get(s);
    }

    public void put(final SubjectIdentifierMembers member, Object o) {
        final String s = member.toString();
        this.put(s, o);
    }

    public void validateSubjectType() throws ValidationException {
        final String subjectType = this.get(SUBJECT_TYPE);
        if (null == subjectType) {
            throw new ValidationException("Subject Identifier member subject_type must be present.");
        }
        if (SubjectIdentifierTypes.contains(subjectType) || subjectType.startsWith("x-"))
            return;
        throw new ValidationException("Subject Identifier member subject_type " +
                "must be defined by specification or begin with x-.");
    }

    public void validate() throws ValidationException {
        validateSubjectType();
    }


    public static class Builder {

        private final SubjectIdentifier members = new SubjectIdentifier();

        public Builder issuer(final String iss) {
            final String subjectTypeMember = members.get(SUBJECT_TYPE);
            if (SubjectIdentifierTypes.SAML_ASSERTION_ID.equalsName(subjectTypeMember)) {
                members.put(SAML_ISSUER, iss);
            }
            else {
                members.put(ISSUER, iss);
            }
            return this;
        }

        public Builder subjectType(final String subjectType) {
            members.put(SUBJECT_TYPE, subjectType);
            return this;
        }

        public Builder subjectType(final SubjectIdentifierTypes subjectType) {
            members.put(SUBJECT_TYPE.toString(), subjectType.toString());
            return this;
        }


        public Builder subject(final String sub) {

            members.put(SUBJECT, sub);
            return this;
        }

        public Builder email(final String email) {

            members.put(EMAIL, email);
            return this;
        }

        public Builder phoneNumber(final String phoneNumber) {

            members.put(PHONE, phoneNumber);
            return this;
        }

        public Builder spagID(final String spag_id) {

            members.put(SPAG_ID, spag_id);
            return this;
        }

        public Builder jwtID(final String jwt_id) {

            members.put(JWT_ID, jwt_id);
            return this;
        }

        public Builder samlAssertionID(final String assertionId) {

            members.put(SAML_ASSERTION_ID, assertionId);
            return this;
        }
        public Builder user(final SubjectIdentifier user) {
            members.put(USER, user);
            return this;
        }

        public Builder device(final SubjectIdentifier device) {
            members.put(DEVICE, device);
            return this;
        }

        public Builder session(final SubjectIdentifier session) {
            members.put(SESSION, session);
            return this;
        }


        public Builder member(final String name, final Object value) {
            members.put(name, value);
            return this;
        }

        public SubjectIdentifier build() { return members; }

    }
}



