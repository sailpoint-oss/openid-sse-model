/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class OpenIDSSEProfileTest  {
    /**
     *  Figure 1: Example: 'user-device-session' Subject Identifier with user only
     */

    @Test
    public void Figure1() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();

        SubjectIdentifier container = new SubjectIdentifier.Builder()
                .subjectType( SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .user(subj)
                .build();

        final String figure_text =
                "{\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       }\n" +
                "   }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, container);
        subj.validate();
    }

    /**
     * Figure 2: Example: 'user-device-session' Subject Identifier with user and device
     */

    @Test
    public void Figure2() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();
        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier container = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .user(user)
                .device(device)
                .build();

        final String figure_text ="{\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       },\n" +
                "       \"device\": {\n" +
                "           \"subject_type\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "       }\n" +
                "}\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, container);
        user.validate();
        device.validate();
        container.validate();
    }

    /**
     *  Figure 3: Example: 'user-device-session' Subject Identifier with user and session
     */

    @Test
    public void Figure3() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();
        SubjectIdentifier session = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        SubjectIdentifier container = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .user(user)
                .session(session)
                .build();

        final String figure_text ="   {\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       },\n" +
                "       \"session\": {\n" +
                "           \"subject_type\": \"iss_sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"dMTlD|1600802906337.16|16008.16\"\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, container);
        user.validate();
        session.validate();
        container.validate();
    }

    /**
     * Figure 4: Example: 'jwt-id' Subject Identifier
     */

    @Test
    public void Figure4() throws ParseException, ValidationException {
        SubjectIdentifier jwtid = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.JWT_ID)
                .issuer("https://idp.example.com/123456789/")
                .jwtID("B70BA622-9515-4353-A866-823539EECBC8")
                .build();
        final String figure_text = "   {\n" +
                "       \"subject_type\": \"jwt-id\",\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"B70BA622-9515-4353-A866-823539EECBC8\"\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, jwtid);
        jwtid.validate();
    }

    /**
     * Figure 5: Example: 'saml-assertion-id' Subject Identifier
     */

    @Test
    public void Figure5() throws ParseException, ValidationException {
        SubjectIdentifier samlSI = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.SAML_ASSERTION_ID)
                .issuer("https://idp.example.com/123456789/")
                .samlAssertionID("_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6")
                .build();

        final String figure_text = "   {\n" +
                "       \"subject_type\": \"saml-assertion-id\",\n" +
                "       \"issuer\": \"https://idp.example.com/123456789/\",\n" +
                "       \"assertion_id\": \"_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6\"\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, samlSI);
        samlSI.validate();
    }


    /**
     * Figure 6: Example: SET Containing a SSE Event with an Email Subject Identifier
     */
    @Test()
    public void Figure6() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.EMAIL)
                .email("foo@example.com")
                .build();

        PlainSSEvent evt = new PlainSSEvent.Builder()
                .eventType(SSEventTypes.RISC_ACCOUNT_ENABLED)
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
                "        \"subject_type\": \"email\",\n" +
                "        \"email\": \"foo@example.com\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     * Figure 7: Example: SET Containing a SSE Event with a Subject That Has an Additional SPAG identifier Claim
     */
    @Test()
    public void Figure7() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.EMAIL)
                .email("foo@example.com")
                .spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
                .build();

        PlainSSEvent evt = new PlainSSEvent.Builder()
                .eventType(SSEventTypes.RISC_ACCOUNT_ENABLED)
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
                "        \"subject_type\": \"email\",\n" +
                "        \"email\": \"foo@example.com\",\n" +
                "        \"spag_id\": \"https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     * Figure 8: Example: SET Containing a SSE Event with an Issuer and Subject Identifier
     */
    @Test()
    public void Figure8() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://issuer.example.com/")
                .subject("abc1234")
                .build();

        PlainSSEvent evt = new PlainSSEvent.Builder()
                .eventType(SSEventTypes.RISC_ACCOUNT_ENABLED)
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
                "        \"subject_type\": \"iss_sub\",\n" +
                "        \"iss\": \"https://issuer.example.com/\",\n" +
                "        \"sub\": \"abc1234\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     * Figure 9: Example: SET Containing a SSE Event with a Subject and a Property Claim
     */
    @Test()
    public void Figure9() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.EMAIL)
                .email("foo@example.com")
                .build();

        PlainSSEvent evt = new PlainSSEvent.Builder()
                .eventType(SSEventTypes.CAEP_IPADDR_CHANGED)
                .subject(subj)
                .ipAddress("123.45.67.89")
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
                "    \"https://schemas.openid.net/secevent/caep/event-type/ip-address-changed\": {\n" +
                "      \"subject\": {\n" +
                "        \"subject_type\": \"email\",\n" +
                "        \"email\": \"foo@example.com\"\n" +
                "      },\n" +
                "      \"ip_address\" : \"123.45.67.89\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     *  Figure 10: Example: SET Containing a SSE Event with a SPAG Subject Type
     */
    @Test()
    public void Figure10() throws ParseException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.SPAG)
                .spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
                .build();

        PlainSSEvent evt = new PlainSSEvent.Builder()
                .eventType(SSEventTypes.CAEP_STREAM_UPDATED)
                .subject(subj)
                .status("paused")
                .reason("License is not valid")
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
                "    \"https://schemas.openid.net/secevent/caep/event-type/stream-updated\": {\n" +
                "      \"subject\": {\n" +
                "        \"subject_type\": \"spag\",\n" +
                "        \"spag_id\" : \"https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a\"\n" +
                "      },\n" +
                "      \"status\": \"paused\",\n" +
                "      \"reason\": \"License is not valid\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        subj.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     *  Figure 11: Example: SET Containing a SSE Event with a Compound subject_type
     */
    @Test()
    public void Figure11() throws ParseException, ValidationException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("jane.smith@example.com")
                .build();

        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier userDevice = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .user(user)
                .device(device)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
               .subject(userDevice)
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
                "        \"subject_type\": \"user-device-session\",\n" +
                "        \"user\": {\n" +
                "            \"subject_type\": \"iss_sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"jane.smith@example.com\"\n" +
                "        },\n" +
                "        \"device\": {\n" +
                "            \"subject_type\": \"iss_sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        user.validate();
        device.validate();
        userDevice.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }

    /**
     *  Figure 12: Example: SET Containing a SSE Event with a Proprietary subject_type
     */
    @Test()
    public void Figure12() throws ParseException, ValidationException {
        final String proprietarySubjectType = "x-device-id";

       SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(proprietarySubjectType)
                .member("device_id", "c0384/devices/2354122")
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .subject(device)
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
                "    \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "    \"subject\": {\n" +
                "        \"subject_type\": \"x-device-id\",\n" +
                "        \"device_id\": \"c0384/devices/2354122\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);

        device.validate();
        evt.validate();

        JWTClaimsSet parsedSet = JWTClaimsSet.parse(figure_text);
        SEToken.validate(parsedSet);
    }
}
