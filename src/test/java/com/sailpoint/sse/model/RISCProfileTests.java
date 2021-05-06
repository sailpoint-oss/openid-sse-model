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
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.IssSubSubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifierFormats;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class RISCProfileTests {
    /*
     *  Figure 1:  Example: Account Credential Change Required
     */
    @Test
    public void Figure1() throws ParseException, SIValidationException, ValidationException {
        IssSubSubjectIdentifier subj = new IssSubSubjectIdentifier.Builder()
                .issuer("https://idp.example.com/")
                .subject("7375626A656374")
                .build();

        RISCAccountCredentialChangeRequired evt = new RISCAccountCredentialChangeRequired.Builder()
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1508184845))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "     \"iss\": \"https://idp.example.com/\",\n" +
                "     \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "     \"iat\": 1508184845,\n" +
                "     \"aud\": \"636C69656E745F6964\",\n" +
                "     \"events\": {\n" +
                "       \"https://schemas.openid.net/secevent/risc/event-type/account-credential-change-required\": {\n" +
                "         \"subject\": {\n" +
                "           \"format\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/\",\n" +
                "           \"sub\": \"7375626A656374\"\n" +
                "         }\n" +
                "       }\n" +
                "     }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }


    /**
     * Figure 2: Example: Account Disabled
     */
    @Test
    public void Figure2() throws ParseException, SIValidationException, ValidationException {
        IssSubSubjectIdentifier subj = new IssSubSubjectIdentifier.Builder()
                .issuer("https://idp.example.com/")
                .subject("7375626A656374")
                .build();

        RISCAccountDisabled evt = new RISCAccountDisabled.Builder()
                .reason(RISCAccountDisabledReasons.HIJACKING)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1508184845))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "     \"iss\": \"https://idp.example.com/\",\n" +
                "     \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "     \"iat\": 1508184845,\n" +
                "     \"aud\": \"636C69656E745F6964\",\n" +
                "     \"events\": {\n" +
                "       \"https://schemas.openid.net/secevent/risc/event-type/account-disabled\": {\n" +
                "         \"subject\": {\n" +
                "           \"format\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/\",\n" +
                "           \"sub\": \"7375626A656374\"\n" +
                "         },\n" +
                "         \"reason\": \"hijacking\"\n" +
                "       }\n" +
                "     }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }

    /**
     * Figure 3: Example: Identifier Changed
     */
    @Test
    public void Figure3() throws ParseException, SIValidationException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("john.doe@example.com")
                .build();

        RISCIdentifierChanged evt = new RISCIdentifierChanged.Builder()
                .subject(subj)
                .newValue("john.roe@example.com")
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1508184845))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "     \"iss\": \"https://idp.example.com/\",\n" +
                "     \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "     \"iat\": 1508184845,\n" +
                "     \"aud\": \"636C69656E745F6964\",\n" +
                "     \"events\": {\n" +
                "       \"https://schemas.openid.net/secevent/risc/event-type/identifier-changed\": {\n" +
                "         \"subject\": {\n" +
                "           \"format\": \"email\",\n" +
                "           \"email\": \"john.doe@example.com\"\n" +
                "         },\n" +
                "         \"new-value\": \"john.roe@example.com\"\n" +
                "       }\n" +
                "     }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }

    /**
     * Figure 4: Example: Identifier Recycled
     */
    @Test
    public void Figure4() throws ParseException, SIValidationException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("foo@example.com")
                .build();

        RISCIdentifierRecycled evt = new RISCIdentifierRecycled.Builder()
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1508184845))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "     \"iss\": \"https://idp.example.com/\",\n" +
                "     \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "     \"iat\": 1508184845,\n" +
                "     \"aud\": \"636C69656E745F6964\",\n" +
                "     \"events\": {\n" +
                "       \"https://schemas.openid.net/secevent/risc/event-type/identifier-recycled\": {\n" +
                "         \"subject\": {\n" +
                "           \"format\": \"email\",\n" +
                "           \"email\": \"foo@example.com\"\n" +
                "         }\n" +
                "       }\n" +
                "     }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }
}
