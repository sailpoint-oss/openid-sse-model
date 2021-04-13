/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.sailpoint.sse.model.did.DIDSubjectIdentifier;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class DIDTests {
    /*
     *  https://github.com/richanna/secevent/pull/2
     */
    @Test
    public void Figure1() throws ParseException, ValidationException {
        DIDSubjectIdentifier subj = new DIDSubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.DID)
                .uri("did:example:123456")
                .build();

        final String figure_text = "{\n" +
                "  \"format\": \"did\",\n" +
                "  \"uri\": \"did:example:123456\"\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, subj);
        subj.validate();
    }
}
