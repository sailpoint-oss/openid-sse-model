/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.sailpoint.sse.model.oauth.OAuthTokenIdentifierAlg;
import com.sailpoint.sse.model.oauth.OAuthTokenSubjectIdentifier;
import com.sailpoint.sse.model.oauth.OAuthTokenType;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class OAuthTests {
    /*
     *  https://bitbucket.org/openid/risc/src/master/oauth-event-types-1_0.txt
     */
    @Test
    public void Figure1() throws ParseException, ValidationException {
        OAuthTokenSubjectIdentifier subj = new OAuthTokenSubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierFormats.OAUTH_TOKEN)
                .tokenType(OAuthTokenType.REFRESH_TOKEN)
                .tokenIdentifierAlg(OAuthTokenIdentifierAlg.PLAIN)
                .token("7265667265736820746F6B656E20737472696E67")
                .build();

        JSONObject obj = new JSONObject();
        obj.put("subject", subj);

        final String figure_text = "{   \"subject\": {\n" +
                "     \"subject_type\": \"oauth_token\",\n" +
                "     \"token_type\": \"refresh_token\",\n" +
                "     \"token_identifier_alg\": \"plain\",\n" +
                "     \"token\": \"7265667265736820746F6B656E20737472696E67\"\n" +
                "   }}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, obj);
        subj.validate();
    }
}
