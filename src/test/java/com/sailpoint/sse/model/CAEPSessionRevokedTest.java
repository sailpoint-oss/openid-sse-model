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
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPSessionRevokedTest {
    /**
     * Figure 1: Example: Session Revoked for User + Session ID + Tenant
     *                              (Complex Subject)
     */

    @Test
    public void Figure1() throws ParseException {
        SubjectIdentifier session = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.OPAQUE)
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        SubjectIdentifier tenant = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.OPAQUE)
                .id("123456789")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .session(session)
                .user(user)
                .tenant(tenant)
                .build();

        SSEvent evt = new CAEPSessionRevoked.Builder()
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("Landspeed Policy Violation: C076E82F")
                .reasonUser("Access attempt from multiple regions.")
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example.com/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"subject\": {\n" +
                "                   \"session\": {\n" +
                "                     \"format\": \"opaque\",\n" +
                "                     \"sub\": \"dMTlD|1600802906337.16|16008.16\"\n" +
                "                   },\n" +
                "                   \"user\": {\n" +
                "                     \"format\": \"iss_sub\",\n" +
                "                     \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                     \"sub\": \"dMTlD|1600802906337.16|16008.16\"\n" +
                "                   },\n" +
                "                   \"tenant\": {\n" +
                "                     \"format\": \"opaque\",\n" +
                "                     \"id\": \"123456789\"\n" +
                "                   }\n" +
                "               },\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Landspeed Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Access attempt from multiple regions.\",\n" +
                "               \"event_timestamp\": 1615304991643\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
    }

    /**
     * Figure 2: Example: Session Revoked for User using sub claim
     */

    @Test
    public void Figure2() throws ParseException {
        SSEvent evt = new CAEPSessionRevoked.Builder()
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("Landspeed Policy Violation: C076E82F")
                .reasonUser("Access attempt from multiple regions.")
                .eventTimestamp(1615304991643L)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example.com/caep")
                .subject("jane.smith@example.com")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"sub\": \"jane.smith@example.com\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Landspeed Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Access attempt from multiple regions.\",\n" +
                "               \"event_timestamp\": 1615304991643\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
    }

    /**
     *        Figure 3: Example: Session Revoked for User + Device + Tenant
     *                              (Complex Subject)
     */

    @Test
    public void Figure3() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("jane.smith@example.com")
                .build();

        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier tenant = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.OPAQUE)
                .id("123456789")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .user(user)
                .device(device)
                .tenant(tenant)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("Policy Violation: C076E82F")
                .reasonUser("This device is no longer compliant.")
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example.com/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"subject\": {\n" +
                "                   \"user\": {\n" +
                "                       \"format\": \"iss_sub\",\n" +
                "                       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                       \"sub\": \"jane.smith@example.com\"\n" +
                "                   },\n" +
                "                   \"device\": {\n" +
                "                       \"format\": \"iss_sub\",\n" +
                "                       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                       \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "                   },\n" +
                "                   \"tenant\": {\n" +
                "                     \"format\": \"opaque\",\n" +
                "                     \"id\": \"123456789\"\n" +
                "                   }\n" +
                "               },\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"This device is no longer compliant.\",\n" +
                "               \"event_timestamp\": 1615304991643\n" +
                "           }\n" +
                "       }\n" +
                "   }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

}