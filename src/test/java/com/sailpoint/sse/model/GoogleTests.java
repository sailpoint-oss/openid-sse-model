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
import com.sailpoint.sse.model.risc.*;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class GoogleTests {
    /*
     *  https://developers.google.com/identity/protocols/risc#handle_events
     *  Note: This uses the original subject_type member and value format with dashes
     *    rather than the newer (April 2021) format "iss_sub"
     */
    @Test
    public void AccountDisabled() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .member("subject_type", "iss-sub")
                .issuer("https://accounts.google.com/")
                .subject("7375626A656374")
                .build();

        RISCAccountDisabled evt = new RISCAccountDisabled.Builder()
                .subject(subj)
                .reason(RISCAccountDisabledReasons.HIJACKING)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://accounts.google.com/")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1508184845L))
                .audience("123456789-abcedfgh.apps.googleusercontent.com")
                .jwtID("756E69717565206964656E746966696572")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://accounts.google.com/\",\n" +
                "  \"aud\": \"123456789-abcedfgh.apps.googleusercontent.com\",\n" +
                "  \"iat\": 1508184845,\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/risc/event-type/account-disabled\": {\n" +
                "      \"subject\": {\n" +
                "        \"subject_type\": \"iss-sub\",\n" +
                "        \"iss\": \"https://accounts.google.com/\",\n" +
                "        \"sub\": \"7375626A656374\"\n" +
                "      },\n" +
                "      \"reason\": \"hijacking\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }
}
