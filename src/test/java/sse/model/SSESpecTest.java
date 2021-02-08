package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class SSESpecTest  {
    /**
     *  Figure 1: Example: 'user-device-session' Subject Identifier with user only
     *
     * @throws ParseException
     */

    @Test
    public void Figure1() throws ParseException {
        JSONObject inner = new JSONObject();
        inner.put("subject_type", SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE);
        inner.put("iss", "https://idp.example.com/123456789/");
        inner.put("sub", "alice@example.com");

        JSONObject container = new JSONObject();
        container.put("subject_type", SubjectIdentifier.USER_DEVICE_SESSION_SUBJECT_IDENTIFIER_TYPE);
        container.put("user", inner);

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
     * @throws ParseException
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
     * @throws ParseException
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
     * @throws ParseException
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
     * @throws ParseException
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


}
