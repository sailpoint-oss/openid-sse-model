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
import com.sailpoint.sse.model.caep.CAEPAssuranceLevelChange;
import com.sailpoint.sse.model.caep.CAEPAssuranceLevelChangeDirection;
import com.sailpoint.sse.model.caep.CAEPInitiatingEntity;
import com.sailpoint.sse.model.caep.NISTAuthenticatorAssuranceLevel;
import com.sailpoint.ietf.subjectidentifiers.model.IssSubSubjectIdentifier;
import com.sailpoint.ietf.subjectidentifiers.model.SIValidationException;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPAssuranceLevelChangeTest {
    /**
     * Figure 9: Example: Assurance Level Increase - Simple Subject +
     *                               optional claims
     */

    @Test
    public void Figure9() throws ParseException, SIValidationException, ValidationException {
        IssSubSubjectIdentifier subj = new IssSubSubjectIdentifier.Builder()
                .issuer("https://idp.example.com/3456789/")
                .subject("jane.smith@example.com")
                .build();

        CAEPAssuranceLevelChange evt = new CAEPAssuranceLevelChange.Builder()
                .currentLevel(NISTAuthenticatorAssuranceLevel.NIST_AAL2)
                .previousLevel(NISTAuthenticatorAssuranceLevel.NIST_AAL1)
                .changeDirection(CAEPAssuranceLevelChangeDirection.INCREASE)
                .eventTimestamp(1615304991643L)
                .initiatingEntity(CAEPInitiatingEntity.USER)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/3456789/")
                .jwtID("07efd930f0977e4fcc1149a733ce7f78")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1615305159L))
                .audience("https://sp.example2.net/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/3456789/\",\n" +
                "       \"jti\": \"07efd930f0977e4fcc1149a733ce7f78\",\n" +
                "       \"iat\": 1615305159,\n" +
                "       \"aud\": \"https://sp.example2.net/caep\",\n" +
                "       \"events\": {\n" +
                "           \"https://schemas.openid.net/secevent/caep/event-type/assurance-level-change\": {\n" +
                "               \"subject\": {\n" +
                "                   \"format\": \"iss_sub\",\n" +
                "                   \"iss\": \"https://idp.example.com/3456789/\",\n" +
                "                   \"sub\": \"jane.smith@example.com\"\n" +
                "               },\n" +
                "               \"current_level\": \"nist-aal2\",\n" +
                "               \"previous_level\": \"nist-aal1\",\n" +
                "               \"change_direction\": \"increase\",\n" +
                "               \"initiating_entity\": \"user\",\n" +
                "               \"event_timestamp\": 1615304991643\n" +
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