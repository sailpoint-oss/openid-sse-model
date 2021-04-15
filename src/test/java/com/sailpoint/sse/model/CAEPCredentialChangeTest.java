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
import com.sailpoint.sse.model.caep.CAEPChangeType;
import com.sailpoint.sse.model.caep.CAEPCredentialChange;
import com.sailpoint.sse.model.caep.CAEPCredentialType;
import com.sailpoint.sse.model.caep.CAEPInitiatingEntity;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPCredentialChangeTest {
    /**
     * Figure 8: Example: Provisioning a new FIDO2 authenticator - Simple
     *                          Subject + optional claims
     */

    @Test
    public void Figure8() throws ParseException, ValidationException {
        IssSubSubjectIdentifier subj = new IssSubSubjectIdentifier.Builder()
                .issuer("https://idp.example.com/3456789/")
                .subject("jane.smith@example.com")
                .build();

        CAEPCredentialChange evt = new CAEPCredentialChange.Builder()
                .credentialType(CAEPCredentialType.FIDO2_ROAMING)
                .changeType(CAEPChangeType.CREATE)
                .fido2AAGuid("accced6a-63f5-490a-9eea-e59bc1896cfc")
                .eventTimestamp(1615304991643L)
                .initiatingEntity(CAEPInitiatingEntity.USER)
                .subject(subj)
                .reasonAdmin("User self-enrollment")
                .friendlyName("Jane's USB authenticator")
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
                "           \"https://schemas.openid.net/secevent/caep/event-type/credential-change\": {\n" +
                "               \"subject\": {\n" +
                "                   \"format\": \"iss_sub\",\n" +
                "                   \"iss\": \"https://idp.example.com/3456789/\",\n" +
                "                   \"sub\": \"jane.smith@example.com\"\n" +
                "               },\n" +
                "               \"credential_type\": \"fido2-roaming\",\n" +
                "               \"change_type\": \"create\",\n" +
                "               \"fido2_aaguid\": \"accced6a-63f5-490a-9eea-e59bc1896cfc\",\n" +
                "               \"friendly_name\": \"Jane's USB authenticator\",\n" +
                "               \"initiating_entity\": \"user\",\n" +
                "               \"reason_admin\": \"User self-enrollment\",\n" +
                "               \"event_timestamp\": 1615304991643\n" +
                "           }\n" +
                "       }\n" +
                "   }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();
        JWTClaimsSet parsedSet = SEToken.parse(figure_text);
    }
}