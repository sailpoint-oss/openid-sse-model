/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import java.net.URI;
import java.net.URISyntaxException;

public class AccountSubjectIdentifier extends SubjectIdentifier {

    /**
     * The Account Identifier Format identifies a subject using an account at a service provider,
     * identified with an acct URI as defined in {{!RFC7565}}. Subject Identifiers in this format MUST contain
     * a uri member whose value is the acct URI for the subject. The uri member is REQUIRED and MUST NOT
     * be null or empty. The Account Identifier Format is identified by the name account.
     *
     * @throws ValidationException - if value is improper per above
     */
    private void validateURI() throws ValidationException {
        Object o = this.get(SubjectIdentifierMembers.URI.toString());
        String s;
        URI uri;
        if (null == o) {
            throw new ValidationException("AccountSubjectIdentifier member uri must be present.");
        }
        if (!((o instanceof String) || (o instanceof URI))) {
            throw new ValidationException("AccountSubjectIdentifier member uri must be a String or URI");
        }
        if (o instanceof String) {
            s = (String) o;
            if (s.equals("")) {
                throw new ValidationException("AccountSubjectIdentifier member uri must not be an empty String.");
            }

            try {
                uri = new URI(s);
            } catch (URISyntaxException e) {
                throw new ValidationException("AccountSubjectIdentifier member uri invalid URI");
            }
        }
        else {
            uri = (URI) o;
        }
        String scheme = uri.getScheme();
        if (null == scheme) {
            throw new ValidationException("AccountSubjectIdentifier member uri must begin with acct: scheme");
        }
        if (!scheme.equals("acct")) {
            throw new ValidationException("AccountSubjectIdentifier member uri must have scheme acct:");
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        validateURI();
    }

    public static class Builder {

        private final AccountSubjectIdentifier members = new AccountSubjectIdentifier();

        public Builder uri(final String uri) {
            members.put(SubjectIdentifierMembers.URI, uri);
            return this;
        }

        public AccountSubjectIdentifier build() {
            members.put(SubjectIdentifierMembers.FORMAT, SubjectIdentifierFormats.ACCOUNT.toString());
            return members;
        }

    }
}



