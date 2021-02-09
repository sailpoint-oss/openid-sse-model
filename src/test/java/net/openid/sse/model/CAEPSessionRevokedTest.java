package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPSessionRevokedTest {
    /**
     * Figure 1: Example: Session Revoked for Session ID
     */

    @Test
    public void Figure1() throws ParseException {
        SubjectIdentifier session = new SubjectIdentifier.Builder()
                .issuer("https://idp.example.com/123456789/")
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE)
                .claim("session", session)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .eventTimestamp(1600975810L)
                .subject(subj)
                .initiatingEntity(CAEPInitiatingEntity.POLICY.toString())
                .reasonAdmin("Policy Violation: C076E82F")
                .reasonUser("Landspeed violation.")
                .tenantID("123456789")
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_SESSION_REVOKED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1600976590L))
                .audience("https://sp.example.com/caep")
                .claim("events", eventType)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1600976590,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"subject\": {\n" +
                "                   \"subject_type\": \"user-device-session\",\n" +
                "                   \"session\": {\n" +
                "                     \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                     \"sub\": \"dMTlD|1600802906337.16|16008.16\"\n" +
                "                   }\n" +
                "               },\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Landspeed violation.\",\n" +
                "               \"tenant_id\": \"123456789\",\n" +
                "               \"event_timestamp\": 1600975810\n" +
                "           }\n" +
                "       }\n" +
                "   }";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String set_json = set.toString();
        assertEquals(figure_json, set_json);
    }

    /**
     * Figure 2: Example: Session Revoked for User using sub claim
     */

    @Test
    public void Figure2() throws ParseException {
        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .eventTimestamp(1600975810L)
                .initiatingEntity(CAEPInitiatingEntity.POLICY.toString())
                .reasonAdmin("Policy Violation: C076E82F")
                .reasonUser("Landspeed violation.")
                .tenantID("123456789")
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_SESSION_REVOKED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1600976590L))
                .audience("https://sp.example.com/caep")
                .subject("jane.smith@example.com")
                .claim("events", eventType)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1600976590,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"sub\": \"jane.smith@example.com\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Landspeed violation.\",\n" +
                "               \"tenant_id\": \"123456789\",\n" +
                "               \"event_timestamp\": 1600975810\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String set_json = set.toString();
        assertEquals(figure_json, set_json);
    }

    /**
     * Figure 3: Example: Session Revoked for User + Device
     */

    @Test
    public void Figure3() throws ParseException {
        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("jane.smith@example.com")
                .build();

        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE)
                .claim("user", user)
                .claim("device", device)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .subject(subj)
                .initiatingEntity(CAEPInitiatingEntity.POLICY.toString())
                .reasonAdmin("Policy Violation: C076E82F")
                .reasonUser("Your device is no longer compliant.")
                .tenantID("123456789")
                .eventTimestamp(1600975810L)
                .build();

        JSONObject eventType = new JSONObject();
        eventType.put(SSEventTypes.CAEP_SESSION_REVOKED.toString(), evt);

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1600976590L))
                .audience("https://sp.example.com/caep")
                .claim("events", eventType)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1600976590,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/session-revoked\": {\n" +
                "               \"subject\": {\n" +
                "                   \"subject_type\": \"user-device-session\",\n" +
                "                   \"user\": {\n" +
                "                       \"subject_type\": \"iss-sub\",\n" +
                "                       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                       \"sub\": \"jane.smith@example.com\"\n" +
                "                   },\n" +
                "                   \"device\": {\n" +
                "                       \"subject_type\": \"iss-sub\",\n" +
                "                       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                       \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "                   }\n" +
                "               },\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Your device is no longer compliant.\",\n" +
                "               \"tenant_id\": \"123456789\",\n" +
                "               \"event_timestamp\": 1600975810\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        final String set_json = set.toString();
        assertEquals(figure_json, set_json);
    }

}