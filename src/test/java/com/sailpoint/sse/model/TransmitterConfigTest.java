/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TransmitterConfigTest {

    @Test()
    public void Figure15() throws ParseException {
        final String iss = "https://tr.example.com";
        final String ssePrefix = "/sse/mgmt";
        final String[] deliveryMethods = { DeliveryMethods.PUSH.toString(),
                                            DeliveryMethods.POLL.toString() };
        final ArrayList<String> criticalMembers = new ArrayList<>(Arrays.asList("tenant", "user"));

        TransmitterConfig txCfg = new TransmitterConfig.Builder()
                .issuer(iss)
                .jwksUri(iss + "/jwks.json")
                .deliveryMethods(Arrays.asList(deliveryMethods))
                .configurationEndpoint(iss + ssePrefix + "/stream")
                .statusEndpoint(iss + ssePrefix + "/status")
                .addSubjectEndpoint(iss + ssePrefix + "/subject:add")
                .removeSubjectEndpoint(iss + ssePrefix + "/subject:remove")
                .verificationEndpoint(iss + ssePrefix + "/verification")
                .criticalSubjectMembers(criticalMembers)
                .build();

        final String figure_text = "   {\n" +
                "     \"issuer\":\n" +
                "       \"https://tr.example.com\",\n" +
                "     \"jwks_uri\":\n" +
                "       \"https://tr.example.com/jwks.json\",\n" +
                "     \"delivery_methods_supported\": [\n" +
                "       \"https://schemas.openid.net/secevent/risc/delivery-method/push\",\n" +
                "       \"https://schemas.openid.net/secevent/risc/delivery-method/poll\"],\n" +
                "     \"configuration_endpoint\":\n" +
                "       \"https://tr.example.com/sse/mgmt/stream\",\n" +
                "     \"status_endpoint\":\n" +
                "       \"https://tr.example.com/sse/mgmt/status\",\n" +
                "     \"add_subject_endpoint\":\n" +
                "       \"https://tr.example.com/sse/mgmt/subject:add\",\n" +
                "     \"remove_subject_endpoint\":\n" +
                "       \"https://tr.example.com/sse/mgmt/subject:remove\",\n" +
                "     \"verification_endpoint\":\n" +
                "       \"https://tr.example.com/sse/mgmt/verification\",\n" +
                "     \"critical_subject_members\": [ \"tenant\", \"user\" ]\n" +
                "   }";

        final JSONObject figureJson = new JSONObject(JSONObjectUtils.parse(figure_text));
        assertEquals(figureJson, txCfg);
    }

}
