/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.oauth;

import com.sailpoint.sse.model.SubjectIdentifier;
import com.sailpoint.sse.model.SubjectIdentifierFormats;
import com.sailpoint.sse.model.SubjectIdentifierMembers;
import com.sailpoint.sse.model.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.sailpoint.sse.model.SubjectIdentifierMembers.*;

public class OAuthTokenSubjectIdentifier extends SubjectIdentifier {



    private static void validateSubjectIdentifierMember(final SubjectIdentifier subj, final SubjectIdentifierMembers member, final Class<?> memberCls) throws ValidationException {
        final Object o = subj.get(member.toString());
        if (null == o) {
            throw new ValidationException(subj.getClass().getName() + " member " + member.toString() + " is missing or null.");
        }
        // Strings don't have contains(). So any string is valid.
        if (memberCls == String.class) return;

        // Fun with reflection to invoke contains() if the enum.name has it (and throw an exception if it does not so we can go fix it
        Method methodToFind;
        try {
            methodToFind = memberCls.getMethod("contains", String.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new ValidationException(memberCls.getName() + " does not have a contains() method.");
        }

        try {
            final Boolean present = (Boolean) methodToFind.invoke(memberCls, o.toString());
            if (Boolean.FALSE.equals(present)) {
                throw new ValidationException(subj.getClass().getName() + " member " + member + " has an invalid value.");
            }
        } catch (IllegalAccessException e) {
            throw new ValidationException(subj.getClass().getName() + " member " + member + " IllegalAccessException: " + e);
        } catch (InvocationTargetException e) {
            throw new ValidationException(subj.getClass().getName() + " member " + member + " InvocationTargetException: " + e);
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        final String subjectType = get(SUBJECT_TYPE);
        if (!subjectType.equals(SubjectIdentifierFormats.OAUTH_TOKEN.toString())) {
            throw new ValidationException("OAuth Token Subject Identifiers must have subject_type oauth_token.");
        }
        validateSubjectIdentifierMember(this, TOKEN_TYPE, OAuthTokenType.class);
        validateSubjectIdentifierMember(this, TOKEN_IDENTIFIER_ALG, OAuthTokenIdentifierAlg.class);
        validateSubjectIdentifierMember(this, TOKEN, String.class);

    }

    public static class Builder {

        private final OAuthTokenSubjectIdentifier members = new OAuthTokenSubjectIdentifier();

        public Builder subjectType(final SubjectIdentifierFormats format) {
            members.put(SubjectIdentifierMembers.SUBJECT_TYPE.toString(), format.toString());
            return this;
        }

        public Builder tokenType(final OAuthTokenType tokenType) {
            members.put(TOKEN_TYPE.toString(), tokenType.toString());
            return this;
        }

        public Builder tokenIdentifierAlg(final OAuthTokenIdentifierAlg alg) {
            members.put(SubjectIdentifierMembers.TOKEN_IDENTIFIER_ALG.toString(), alg.toString());
            return this;
        }

        public Builder token(final String token) {
            members.put(SubjectIdentifierMembers.TOKEN.toString(), token);
            return this;
        }

        public OAuthTokenSubjectIdentifier build() {
            return members;
        }

    }
}



