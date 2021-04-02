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
import com.sailpoint.sse.model.caep.CAEPSessionRevoked;
import com.sailpoint.sse.model.caep.CAEPTokenClaimsChange;
import com.sailpoint.sse.model.risc.RISCAccountEnabled;
import org.junit.Test;

import java.text.ParseException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class OpenIDSSEProfileTest {

    private void dumpMapRecursive(Map<String, Object> map, int level) {
        for (String k : map.keySet()) {
            Object o = map.get(k);
            // Can't use String.repeat() here until Java 11, we support 9+ at the moment.
            String sb = IntStream.range(0, level).mapToObj(i -> "\t").collect(Collectors.joining());
            System.out.printf("%s%s -> (%s) %s%n", sb, k, o.getClass(), o.toString());
            if (o instanceof JSONObject) {
                dumpMapRecursive((JSONObject) o, level + 1);
            }
        }
    }

    private void dumpJWT(JWTClaimsSet set) {
        Map<String, Object> map = set.getClaims();
        dumpMapRecursive(map, 1);
    }


    /**
     * Figure 1: Example: Simple Subject
     */
    @Test
    public void Figure1() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("foo@example.com")
                .build();

        JSONObject container = new JSONObject();
        container.put("transferor", subj);

        final String figure_text = "   {\"transferor\": {\n" +
                "     \"format\": \"email\",\n" +
                "     \"email\": \"foo@example.com\"\n" +
                "   }}\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, container);
        subj.validate();
    }

    /**
     * Figure 2: Example: Complex Subject
     */
    @Test
    public void Figure2() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("bar@example.com")
                .build();
        SubjectIdentifier tenant = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.ISSUER_SUBJECT)
                .issuer("http://example.com/idp1")
                .subject("1234")
                .build();
        SubjectIdentifier transferee = new SubjectIdentifier.Builder()
                .user(user)
                .tenant(tenant)
                .build();

        JSONObject container = new JSONObject();
        container.put("transferee", transferee);

        final String figure_text = "   {\"transferee\": {\n" +
                "     \"user\" : {\n" +
                "       \"format\": \"email\",\n" +
                "       \"email\": \"bar@example.com\"\n" +
                "     },\n" +
                "     \"tenant\" : {\n" +
                "       \"format\": \"iss_sub\",\n" +
                "       \"iss\" : \"http://example.com/idp1\",\n" +
                "       \"sub\" : \"1234\"\n" +
                "     }\n" +
                "   }}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, container);
        transferee.validate();
    }

    /**
     * Figure 3: Example: 'jwt-id' Subject Identifier
     */

    @Test
    public void Figure3() throws ParseException, ValidationException {
        SubjectIdentifier jwtid = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.JWT_ID)
                .issuer("https://idp.example.com/123456789/")
                .jwtID("B70BA622-9515-4353-A866-823539EECBC8")
                .build();
        final String figure_text = "   {\n" +
                "       \"format\": \"jwt_id\",\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"B70BA622-9515-4353-A866-823539EECBC8\"\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, jwtid);
        jwtid.validate();
    }

    /**
     * Figure 4:    {
     * "format": "saml-assertion-id",
     * "issuer": "https://idp.example.com/123456789/",
     * "assertion_id": "_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6"
     * }
     */
    @Test
    public void Figure4() throws ParseException, ValidationException {
        SubjectIdentifier jwtid = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.SAML_ASSERTION_ID)
                .issuer("https://idp.example.com/123456789/")
                .samlAssertionID("_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6")
                .build();

        final String figure_text = "   {\n" +
                "       \"format\": \"saml_assertion_id\",\n" +
                "       \"issuer\": \"https://idp.example.com/123456789/\",\n" +
                "       \"assertion_id\": \"_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6\"\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, jwtid);
        jwtid.validate();
    }

    /**
     * Figure 5: Example: SET Containing a SSE Event with a Simple Subject Claim
     */

    @Test
    public void Figure5() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("foo@example.com")
                .build();

        RISCAccountEnabled evt = new RISCAccountEnabled.Builder()
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://idp.example.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/risc/event-type/account-enabled\": {\n" +
                "      \"subject\": {\n" +
                "        \"format\": \"email\",\n" +
                "        \"email\": \"foo@example.com\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }


    /**
     * Figure 6: Example: SET Containing a SSE Event with a Complex Subject Claim
     */

    @Test
    public void Figure6() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("jane.smith@example.com")
                .build();
        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .user(user)
                .device(device)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://idp.example.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "    \"subject\": {\n" +
                "        \"user\": {\n" +
                "            \"format\": \"iss_sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"jane.smith@example.com\"\n" +
                "        },\n" +
                "        \"device\": {\n" +
                "            \"format\": \"iss_sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "        }\n" + // closes device
                "      },\n" + // closes subject
                "   \"event_timestamp\": 1615304991643\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();

        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }


    /**
     * Figure 7: Example: SET Containing a SSE Event with a Simple Subject
     * and a Property Claim
     */
    @Test()
    public void Figure7() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("foo@example2.com")
                .build();

        JSONObject newClaims = new JSONObject();
        newClaims.put("ip_address", "123.45.67.89");

        CAEPTokenClaimsChange evt = new CAEPTokenClaimsChange.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .claims(newClaims)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://sp.example2.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://sp.example2.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/caep/event-type/token-claims-change\": {\n" +
                "      \"subject\": {\n" +
                "        \"format\": \"email\",\n" +
                "        \"email\": \"foo@example2.com\"\n" +
                "      },\n" +
                "    \"claims\": {\n" +
                "      \"ip_address\" : \"123.45.67.89\"\n" +
                "     },\n" +
                "    \"event_timestamp\": 1615304991643\n" +
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

    /**
     * Figure 8: Example: SET Containing a SSE Event with a Proprietary format
     */
    @Test()
    public void Figure8() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .member("format", "x-catalog-item")
                .member("catalog_id", "c0384/winter/2354122")
                .build();

        JSONObject newClaims = new JSONObject();
        newClaims.put("some_claim", "some_value");


        CAEPTokenClaimsChange evt = new CAEPTokenClaimsChange.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .claims(newClaims)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://myservice.example3.com/")
                .jwtID("756E69717565206964656E746966696534")
                .issueTime(DateUtils.fromSecondsSinceEpoch(15203800012L))
                .audience("636C69656E745F6324")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://myservice.example3.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696534\",\n" +
                "  \"iat\": 15203800012,\n" +
                "  \"aud\": \"636C69656E745F6324\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/caep/event-type/token-claims-change\": {\n" +
                "    \"subject\": {\n" +
                "        \"format\": \"x-catalog-item\",\n" +
                "        \"catalog_id\": \"c0384/winter/2354122\"\n" +
                "      },\n" +
                "    \"claims\": {\n" +
                "      \"some_claim\" : \"some_value\"\n" +
                "     },\n" +
                "      \"event_timestamp\": 1615304991643\n" +
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

    /**
     * SET Containing a SSE Event with a Simple Subject Claim
     * and an undefined event type
     */

    @Test
    public void UndefinedEventParseTest() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.EMAIL)
                .email("foo@example.com")
                .build();

        NonstandardSSEvent evt = new NonstandardSSEvent.Builder()
                .eventName("https://schemas.openid.net/secevent/unknown/event-type/unknown")
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://idp.example.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/unknown/event-type/unknown\": {\n" +
                "      \"subject\": {\n" +
                "        \"format\": \"email\",\n" +
                "        \"email\": \"foo@example.com\"\n" +
                "      }\n" +
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

