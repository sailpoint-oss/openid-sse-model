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
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CAEPDeviceComplianceChangeTest {
    /**
     * Figure 8: Example: Device has gone out of compliance
     */

    @Test
    public void Figure8() throws ParseException, ValidationException {
        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .device(device)
                .build();

        CAEPDeviceComplianceChange evt = new CAEPDeviceComplianceChange.Builder()
                .previousStatus(CAEPComplianceStatus.COMPLIANT)
                .currentStatus(CAEPComplianceStatus.NOT_COMPLIANT)
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("Location Policy Violation: C076E82F")
                .reasonUser("Device is no longer in a trusted location.")
                .tenantID("123456789")
                .eventTimestamp(1600975810L)
                .subject(subj)
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID("24c63fb56e5a2d77a6b512616ca9fa24")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1600976590L))
                .audience("https://sp.example.com/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

        final String figure_text = "   {\n" +
                "       \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "       \"jti\": \"24c63fb56e5a2d77a6b512616ca9fa24\",\n" +
                "       \"iat\": 1600976590,\n" +
                "       \"aud\": \"https://sp.example.com/caep\",\n" +
                "       \"events\": {\n" +
                "            \"https://schemas.openid.net/secevent/caep/event-type/device-compliance-change\": {\n" +
                "                \"subject\": {\n" +
                "                    \"subject_type\": \"user-device-session\",\n" +
                "                    \"device\": {\n" +
                "                        \"subject_type\": \"iss_sub\",\n" +
                "                        \"iss\": \"https://idp.example.com/123456789/\",\n" +
                "                        \"sub\": \"e9297990-14d2-42ec-a4a9-4036db86509a\"\n" +
                "                     }\n" +
                "                },\n" +
                "               \"current_status\": \"not-compliant\",\n" +
                "               \"previous_status\": \"compliant\",\n" +
                "               \"initiating_entity\": \"policy\",\n" +
                "               \"reason_admin\": \"Location Policy Violation: C076E82F\",\n" +
                "               \"reason_user\": \"Device is no longer in a trusted location.\",\n" +
                "               \"tenant_id\": \"123456789\",\n" +
                "               \"event_timestamp\": 1600975810\n" +
                "           }\n" +
                "       }\n" +
                "   }\n";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        final JSONObject setJson = new JSONObject(set.toJSONObject());
        assertEquals(figureJson, setJson);
        evt.validate();
    }
}