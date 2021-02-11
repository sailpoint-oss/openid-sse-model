package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONObjectUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TransmitterConfigTest {

    @Test()
    public void Figure15() throws ParseException {
        final String iss = "https://tr.example.com";
        final String riscPrefix = "/risc/mgmt";
        final String[] deliveryMethods = { DeliveryMethods.PUSH.toString(),
                                            DeliveryMethods.POLL.toString() };

        TransmitterConfig txCfg = new TransmitterConfig.Builder()
                .issuer(iss)
                .jwksUri(iss + "/jwks.json")
                .deliveryMethods(Arrays.asList(deliveryMethods))
                .configurationEndpoint(iss +riscPrefix + "/stream")
                .statusEndpoint(iss + riscPrefix + "/status")
                .addSubjectEndpoint(iss + riscPrefix + "/subject:add")
                .removeSubjectEndpoint(iss + riscPrefix + "/subject:remove")
                .verificationEndpoint(iss + riscPrefix + "/verification")
                .build();

        String tc_json = txCfg.toString();

        final String figure_text = "{\n" +
                "     \"issuer\":\n" +
                "       \"https://tr.example.com\",\n" +
                "     \"jwks_uri\":\n" +
                "       \"https://tr.example.com/jwks.json\",\n" +
                "     \"delivery_methods_supported\": [\n" +
                "       \"https://schemas.openid.net/secevent/risc/delivery-method/push\",\n" +
                "       \"https://schemas.openid.net/secevent/risc/delivery-method/poll\"],\n" +
                "     \"configuration_endpoint\":\n" +
                "       \"https://tr.example.com/risc/mgmt/stream\",\n" +
                "     \"status_endpoint\":\n" +
                "       \"https://tr.example.com/risc/mgmt/status\",\n" +
                "     \"add_subject_endpoint\":\n" +
                "       \"https://tr.example.com/risc/mgmt/subject:add\",\n" +
                "     \"remove_subject_endpoint\":\n" +
                "       \"https://tr.example.com/risc/mgmt/subject:remove\",\n" +
                "     \"verification_endpoint\":\n" +
                "       \"https://tr.example.com/risc/mgmt/verification\"\n" +
                "   }";

        // TODO: replace String comparison with JSONObject comparison
        final String figure_json = JSONObject.toJSONString((JSONObjectUtils.parse(figure_text)));
        assertEquals(figure_json, tc_json);



    }

}
