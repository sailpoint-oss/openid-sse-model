package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class SSESpecTest  {
    @Test
    public void Figure1() throws ParseException {
        JSONObject inner = new JSONObject();
        inner.put("subject_type", "iss-sub");
        inner.put("iss", "https://idp.example.com/123456789/");
        inner.put("sub", "alice@example.com");

        JSONObject container = new JSONObject();
        container.put("subject_type", "user-device-session");
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

    @Test
    public void Figure2() throws ParseException {
        JSONObject user = new SubjectIdentifier.Builder()
                .subjectType("iss-sub")
                .issuer("https://idp.example.com/123456789/")
                .subject("alice@example.com")
                .build();
        JSONObject device = new SubjectIdentifier.Builder()
                .subjectType("iss-sub")
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        JSONObject container = new JSONObject();
        container.put("subject_type", "user-device-session");
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
}
