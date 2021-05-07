/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import com.sailpoint.sse.model.sse.SSEStreamUpdated;
import com.sailpoint.sse.model.sse.SSEVerification;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.IssSubSubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifierFormats;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class EventStreamTests {
    /**
     * Figure 31: Example: Verification SET
     */
    @Test()
    public void Figure31() throws ParseException, SIValidationException, ValidationException {

        SSEVerification evt = new SSEVerification.Builder()
                .state("VGhpcyBpcyBhbiBleGFtcGxlIHN0YXRlIHZhbHVlLgo=")
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://transmitter.example.com")
                .jwtID("123456")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1493856000L))
                .audience("receiver.example.com")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = " {\n" +
                "   \"jti\": \"123456\",\n" +
                "   \"iss\": \"https://transmitter.example.com\",\n" +
                "   \"aud\": \"receiver.example.com\",\n" +
                "   \"iat\": 1493856000,\n" +
                "   \"events\": {\n" +
                "     \"https://schemas.openid.net/secevent/sse/event-type/verification\":{\n" +
                "       \"state\": \"VGhpcyBpcyBhbiBleGFtcGxlIHN0YXRlIHZhbHVlLgo=\"\n" +
                "     }\n" +
                "   }\n" +
                " }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }

    /**
     * Figure 32: Example: Stream Updated SET
     */
    @Test()
    public void Figure32() throws ParseException, SIValidationException, ValidationException {
        IssSubSubjectIdentifier tenant = new IssSubSubjectIdentifier.Builder()
                .issuer("http://example.com/idp1")
                .subject("1234")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .tenant(tenant)
                .build();

        SSEStreamUpdated evt = new SSEStreamUpdated.Builder()
                .subject(subj)
                .status(StreamStatus.PAUSED)
                .reason("License is not valid")
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://transmitter.example.com")
                .jwtID("123456")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1493856000L))
                .audience("receiver.example.com")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"jti\": \"123456\",\n" +
                "  \"iss\": \"https://transmitter.example.com\",\n" +
                "  \"aud\": \"receiver.example.com\",\n" +
                "  \"iat\": 1493856000,\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/sse/event-type/stream-updated\": {\n" +
                "      \"subject\": {\n" +
                "        \"tenant\" : {\n" +
                "          \"format\": \"iss_sub\",\n" +
                "          \"iss\" : \"http://example.com/idp1\",\n" +
                "          \"sub\" : \"1234\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"status\": \"paused\",\n" +
                "      \"reason\": \"License is not valid\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }

}
