package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPAssuranceLevelChangeTest {
    /**
     * Figure 7: Example: Assurance Level Increase
     */

    @Test
    public void Figure7() throws ParseException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3456789/")
                .subject("jane.smith@example.com")
                .build();

        SSEvent evt = new CAEPAssuranceLevelChange.Builder()
                .currentLevel(NISTAuthenticatorAssuranceLevel.NIST_AAL2)
                .previousLevel(NISTAuthenticatorAssuranceLevel.NIST_AAL1)
                .changeDirection(AssuranceLevelChangeDirection.INCREASE)
                .eventTimestamp(1600975811L)
                .initiatingEntity(CAEPInitiatingEntity.USER)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/3456789/")
                .jwtID("07efd930f0977e4fcc1149a733ce7f78")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1600976598L))
                .audience("https://sp.example2.net/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/3456789/\",\n" +
                "       \"jti\": \"07efd930f0977e4fcc1149a733ce7f78\",\n" +
                "       \"iat\": 1600976598,\n" +
                "       \"aud\": \"https://sp.example2.net/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/assurance-level-change\": {\n" +
                "               \"subject\": {\n" +
                "                   \"subject_type\": \"iss_sub\",\n" +
                "                   \"iss\": \"https://idp.example.com/3456789/\",\n" +
                "                   \"sub\": \"jane.smith@example.com\"\n" +
                "               },\n" +
                "               \"current_level\": \"nist-aal2\",\n" +
                "               \"previous_level\": \"nist-aal1\",\n" +
                "               \"change_direction\": \"increase\",\n" +
                "               \"initiating_entity\": \"user\",\n" +
                "               \"event_timestamp\": 1600975811\n" +
                "           }\n" +
                "       }\n" +
                "   }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
    }
}