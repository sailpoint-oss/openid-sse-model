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
import com.sailpoint.sse.model.caep.CAEPInitiatingEntity;
import com.sailpoint.sse.model.caep.CAEPTokenClaimsChange;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.SubjectIdentifierFormats;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPTokenClaimsChangeTest {
    /**
     * Figure 5: Example: OIDC ID Token Claims Change - Required claims only
     */

    @Test
    public void Figure5() throws ParseException, SIValidationException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.JWT_ID)
                .issuer("https://idp.example.com/987654321/")
                .jwtID("f61t6e20zdo3px56gepu8rzlsp4c1dpc0fx7")
                .build();

        JSONObject claims = new JSONObject();
        claims.put("role", "ro-admin");

        CAEPTokenClaimsChange evt = new CAEPTokenClaimsChange.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .claims(claims)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/987654321/")
                .jwtID("9afce1e4e642b165fcaacdd0e7aa4903")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example2.net/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/987654321/\",\n" +
                "       \"jti\": \"9afce1e4e642b165fcaacdd0e7aa4903\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example2.net/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/token-claims-change\": {\n" +
                "               \"subject\": {\n" +
                "                   \"format\": \"jwt_id\",\n" +
                "                   \"iss\": \"https://idp.example.com/987654321/\",\n" +
                "                   \"jti\": \"f61t6e20zdo3px56gepu8rzlsp4c1dpc0fx7\"\n" +
                "               },\n" +
                "               \"event_timestamp\": 1615304991643,\n" +
                "               \"claims\": {\n" +
                "                   \"role\": \"ro-admin\"\n" +
                "               }\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();
        JWTClaimsSet parsedSet = SEToken.parse(figure_text);

    }
    /**
     * Figure 6: Example: OIDC ID Token Claims Change - Optional claims
     */

    @Test
    public void Figure6() throws ParseException, SIValidationException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.JWT_ID)
                .issuer("https://idp.example.com/987654321/")
                .jwtID("f61t6e20zdo3px56gepu8rzlsp4c1dpc0fx7")
                .build();

        JSONObject claims = new JSONObject();
        claims.put("trusted_network", "false");

        CAEPTokenClaimsChange evt = new CAEPTokenClaimsChange.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .claims(claims)
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("User left trusted network: CorpNet3")
                .reasonUser("You're no longer connected to a trusted network.")
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/987654321/")
                .jwtID("9afce1e4e642b165fcaacdd0e7aa4903")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example2.net/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "{\n" +
                "    \"iss\": \"https://idp.example.com/987654321/\",\n" +
                "    \"jti\": \"9afce1e4e642b165fcaacdd0e7aa4903\",\n" +
                "    \"iat\": 1615305159,\n" +
                "    \"aud\": \"https://sp.example2.net/caep\",\n" +
                "    \"events\": {\n" +
                "        \"https://schemas.openid.net/secevent/caep/event-type/token-claims-change\": {\n" +
                "            \"subject\": {\n" +
                "                \"format\": \"jwt_id\",\n" +
                "                \"iss\": \"https://idp.example.com/987654321/\",\n" +
                "                \"jti\": \"f61t6e20zdo3px56gepu8rzlsp4c1dpc0fx7\"\n" +
                "            },\n" +
                "            \"event_timestamp\": 1615304991643,\n" +
                "            \"initiating_entity\": \"policy\",\n" +
                "            \"reason_admin\": \"User left trusted network: CorpNet3\",\n" +
                "            \"reason_user\": \"You're no longer connected to a trusted network.\",\n" +
                "            \"claims\": {\n" +
                "                \"trusted_network\": \"false\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();
        JWTClaimsSet parsedSet = SEToken.parse(figure_text);

    }

    /**
     * Figure 7: Example: SAML Assertion Claims Change - Required claims
     *                                    only
     */

    @Test
    public void Figure7() throws ParseException, SIValidationException, ValidationException {
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .format(SubjectIdentifierFormats.SAML_ASSERTION_ID)
                .issuer("https://idp.example.com/987654321/")
                .samlAssertionID("_a75adf55-01d7-dbd8372ebdfc")
                .build();

        JSONObject claims = new JSONObject();
        claims.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/role",
                "ro-admin");

        CAEPTokenClaimsChange evt = new CAEPTokenClaimsChange.Builder()
                .eventTimestamp(1615304991643L)
                .subject(subj)
                .member("claims", claims)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/987654321/")
                .jwtID("dae94fed5f459881efa38b65c6772ddc")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example2.net/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/987654321/\",\n" +
                "       \"jti\": \"dae94fed5f459881efa38b65c6772ddc\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example2.net/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/token-claims-change\": {\n" +
                "               \"subject\": {\n" +
                "                   \"format\": \"saml_assertion_id\",\n" +
                "                   \"issuer\": \"https://idp.example.com/987654321/\",\n" +
                "                   \"assertion_id\": \"_a75adf55-01d7-dbd8372ebdfc\"\n" +
                "               },\n" +
                "               \"event_timestamp\": 1615304991643,\n" +
                "               \"claims\": {\n" +
                "                   \"http://schemas.xmlsoap.org/ws/2005/05/identity/claims/role\": \"ro-admin\"\n" +
                "               }\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();
        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }
}