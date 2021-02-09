package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class OpenIDSSEProfileTest  {
    /**
     *  Figure 1: Example: 'user-device-session' Subject Identifier with user only
     */

    @Test
    public void Figure1() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();

        JSONObject container = new JSONObject();
        container.put("subject_type", SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE);
        container.put("user", subj);

        final String fig_1_text =
                "{\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss-sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       }\n" +
                "   }";

        final String fig_1_json = JSONObject.toJSONString((JSONObjectUtils.parse(fig_1_text)));
        final String container_json = JSONObject.toJSONString(container);
        assertEquals(fig_1_json, container_json);
    }

    /**
     * Figure 2: Example: 'user-device-session' Subject Identifier with user and device
     */

    @Test
    public void Figure2() throws ParseException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();
        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        JSONObject container = new JSONObject();
        container.put("subject_type", SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE);
        container.put("user", user);
        container.put("device", device);

        final String figure_text ="{\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss-sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       },\n" +
                "       \"device\": {\n" +
                "           \"subject_type\": \"iss-sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "       }\n" +
                "}\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String container_json = JSONObject.toJSONString(container);
        assertEquals(figure_json, container_json);
    }

    /**
     *  Figure 3: Example: 'user-device-session' Subject Identifier with user and session
     */

    @Test
    public void Figure3() throws ParseException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();
        SubjectIdentifier session = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        JSONObject container = new JSONObject();
        container.put("subject_type", SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE);
        container.put("user", user);
        container.put("session", session);

        final String figure_text ="   {\n" +
                "       \"subject_type\": \"user-device-session\",\n" +
                "       \"user\": {\n" +
                "           \"subject_type\": \"iss-sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"alice@example.com\"\n" +
                "       },\n" +
                "       \"session\": {\n" +
                "           \"subject_type\": \"iss-sub\",\n" +
                "           \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "           \"sub\": \"dMTlD|1600802906337.16|16008.16\"\n" +
                "       }\n" +
                "   }\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String container_json = JSONObject.toJSONString(container);
        assertEquals(figure_json, container_json);
    }

    /**
     * Figure 4: Example: 'jwt-id' Subject Identifier
     */

    @Test
    public void Figure4() throws ParseException {
        SubjectIdentifier jwtid = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.JWT_ID_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .jwtID("B70BA622-9515-4353-A866-823539EECBC8")
                .build();
        final String figure_text = "   {\n" +
                "       \"subject_type\": \"jwt-id\",\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"B70BA622-9515-4353-A866-823539EECBC8\"\n" +
                "   }\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String jwtid_json = JSONObject.toJSONString(jwtid);
        assertEquals(figure_json, jwtid_json);
    }

    /**
     * Figure 5: Example: 'saml-assertion-id' Subject Identifier
     */

    @Test
    public void Figure5() throws ParseException {
        SubjectIdentifier samlSI = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.SAML_ASSERTION_ID_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .samlAssertionID("_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6")
                .build();

        final String figure_text = "   {\n" +
                "       \"subject_type\": \"saml-assertion-id\",\n" +
                "       \"issuer\": \"https://idp.example.com/123456789/\",\n" +
                "       \"assertion_id\": \"_8e8dc5f69a98cc4c1ff3427e5ce34606fd672f91e6\"\n" +
                "   }\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String samlsi_json = JSONObject.toJSONString(samlSI);
        assertEquals(figure_json, samlsi_json);
    }


    /**
     * Figure 6: Example: SET Containing a SSE Event with an Email Subject Identifier
     */
    @Test()
    public void Figure6() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
                .email("foo@example.com")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(subj)
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
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

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        String json = set.toString();
        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     * Figure 7: Example: SET Containing a SSE Event with a Subject That Has an Additional SPAG identifier Claim
     */
    @Test()
    public void Figure7() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
                .email("foo@example.com")
                .spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(subj)
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
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

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        String json = set.toString();
        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     * Figure 8: Example: SET Containing a SSE Event with an Issuer and Subject Identifier
     */
    @Test()
    public void Figure8() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://issuer.example.com/")
                .subject("abc1234")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(subj)
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://idp.example.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/risc/event-type/account-enabled\": {\n" +
                "      \"subject\": {\n" +
                "        \"subject_type\": \"iss-sub\",\n" +
                "        \"iss\": \"https://issuer.example.com/\",\n" +
                "        \"sub\": \"abc1234\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     * Figure 9: Example: SET Containing a SSE Event with a Subject and a Property Claim
     */
    @Test()
    public void Figure9() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
                .email("foo@example.com")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(subj)
                .ipAddress("123.45.67.89")
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_IPADDR_CHANGED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://sp.example2.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
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

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     *  Figure 10: Example: SET Containing a SSE Event with a SPAG Subject Type
     */
    @Test()
    public void Figure10() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.SPAG_SUBJECT_IDENTIFIER_TYPE)
                .spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(subj)
                .status("paused")
                .reason("License is not valid")
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_STREAM_UPDATED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://sp.example2.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
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

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     *  Figure 11: Example: SET Containing a SSE Event with a Compound subject_type
     */
    @Test()
    public void Figure11() throws ParseException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("jane.smith@example.com")
                .build();

        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier userDevice = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.USER_DEVICE_SUBJECT_IDENTIFIER_TYPE)
                .user(user)
                .device(device)
                .build();

        SSEvent evt = new SSEvent.Builder()
               .subject(userDevice)
               .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_SESSION_REVOKED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, eventType)
                .build();

        final String figure_text = "{\n" +
                "  \"iss\": \"https://idp.example.com/\",\n" +
                "  \"jti\": \"756E69717565206964656E746966696572\",\n" +
                "  \"iat\": 1520364019,\n" +
                "  \"aud\": \"636C69656E745F6964\",\n" +
                "  \"events\": {\n" +
                "    \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "    \"subject\": {\n" +
                "        \"subject_type\": \"user-device\",\n" +
                "        \"user\": {\n" +
                "            \"subject_type\": \"iss-sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"jane.smith@example.com\"\n" +
                "        },\n" +
                "        \"device\": {\n" +
                "            \"subject_type\": \"iss-sub\",\n" +
                "            \"iss\": \"https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/\",\n" +
                "            \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }

    /**
     *  Figure 12: Example: SET Containing a SSE Event with a Proprietary subject_type
     */
    @Test()
    public void Figure12() throws ParseException {
        final String proprietarySubjectType = "x-device-id";

       SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(proprietarySubjectType)
                .claim("device_id", "c0384/devices/2354122")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .subject(device)
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_SESSION_REVOKED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://myservice.example3.com/")
                .jwtID("756E69717565206964656E746966696534")
                .issueTime(DateUtils.fromSecondsSinceEpoch(15203800012L))
                .audience("636C69656E745F6324")
                .claim(SEToken.EVENTS_CLAIM, eventType)
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

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String JWTClaimsSet_json = set.toString();
        assertEquals(figure_json, JWTClaimsSet_json);

        JWTClaimsSet setNew = JWTClaimsSet.parse(figure_json);
    }
}
