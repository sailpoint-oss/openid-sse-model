package sse.scaffolding;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import sse.json.ObjectMapperStandard;
import sse.model.SEToken;
import sse.model.SSEvent;
import sse.model.SSEventTypes;
import sse.model.Subject;
import sse.model.Subject.SubjectCategories;
import sse.model.TransmitterConfig;

public class JsonRoundTrip {

	public static void main(String[] args) {

		System.err.println("");
		System.out.println("JSON Round Trip Scaffolding");

		{ 
			System.out.println("# Draft Figure 1, 'SET Containing a SSE Event with an Email Subject Identifier', page 6 top:");
			Subject subj = new Subject();
			subj.setSubjectType(Subject.SubjectTypes.email);
			subj.setSubject("foo@example.com");

			SSEvent evt = new SSEvent();
			evt.setEventType(SSEventTypes.ACCOUNT_ENABLED);
			evt.setSubject(subj);

			SEToken set = new SEToken();
			set.setIssuer("https://idp.example.com/");
			set.setJti("756E69717565206964656E746966696572");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F6964");
			set.addEvent(evt);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
		}

		{ 
			System.out.println("# Draft Figure 2, 'Example SET', page 6 bottom:");
			Subject subj = new Subject();
			subj.setSubjectType(Subject.SubjectTypes.iss_sub);
			subj.setIssuer("https://issuer.example.com/");
			subj.setSubject("abc1234");

			SSEvent evt = new SSEvent();
			evt.setEventType(SSEventTypes.ACCOUNT_ENABLED);
			evt.setSubject(subj);

			SEToken set = new SEToken();
			set.setIssuer("https://idp.example.com/");
			set.setJti("756E69717565206964656E746966696572");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F6964");
			set.addEvent(evt);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
		}

		{
			// Reproduce Atul's draft text from `openid-sse-profile-draft2` page 7.
			System.out.println("# Draft Figure 3, 'Example SET', page 7 top:");
			Subject subj = new Subject();
			subj.setSubjectType(Subject.SubjectTypes.email);
			subj.setSubject("foo@example.com");

			SSEvent evt = new SSEvent();
			evt.setEventType(SSEventTypes.IPADDR_CHAGNED);
			evt.setSubject(subj);
			evt.setIpAddress("123.45.67.89"); // From CAEP example, ha!

			SEToken set = new SEToken();
			set.setIssuer("https://sp.example2.com/");
			set.setJti("756E69717565206964656E746966696572");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F6964");
			set.addEvent(evt);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
		}

		{
			System.out.println("# Draft Figure 4, 'SET Containing a SSE Event with a SPAG Subject Type', page 7 bottom:");
			Subject subj = new Subject();
			subj.setSubjectType(Subject.SubjectTypes.spag);
			subj.setSpagId("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a");

			SSEvent evt = new SSEvent();
			evt.setEventType(SSEventTypes.IPADDR_CHAGNED);
			evt.setSubject(subj);

			SEToken set = new SEToken();
			set.setIssuer("https://sp.example2.com/");
			set.setJti("756E69717565206964656E746966696572");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F6964");
			set.addEvent(evt);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
		}


		{
			System.out.println("# Draft Figure 5, 'SET Containing a SSE Event with Common Claims in the Subject', page 8 top:");

			Subject subj = new Subject();
			subj.setSubjectType(Subject.SubjectTypes.id_token_claims);
			subj.setCategory(SubjectCategories.device);
			subj.setSpagId("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a");
			subj.setPhoneNumber("+1 (408) 555-1212");

			SSEvent evt = new SSEvent();
			evt.setEventType(SSEventTypes.IPADDR_CHAGNED);
			evt.setSubject(subj);

			SEToken set = new SEToken();
			set.setIssuer("https://sp.example2.com/");
			set.setJti("756E69717565206964656E746966696572");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F6964");
			set.addEvent(evt);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
		}

		{
			System.out.println("TransmitterConfig JSON example:");

			TransmitterConfig txCfg = new TransmitterConfig();

			String iss = "https://ssedemo.identitynow.com";
			txCfg.setIssuer(iss);
			txCfg.setJwksUri(iss + "/jwks");
			txCfg.setSupportedVersions("1.0");
			txCfg.setConfigurationEndpoint(iss + "/config");
			txCfg.setStatusEndpoint(iss + "/status");
			txCfg.setAddSubjectEndpoint(iss + "/addSubject");
			txCfg.setRemoveSubjectEndpoint(iss + "/removeSubject");
			txCfg.setVerificationEndpoint(iss + "/verify");

			ArrayList<String> deliveryMethods = new ArrayList<String>();
			deliveryMethods.add("https");
			txCfg.setDeliveryMethodsSupported(deliveryMethods);

			String json  = ObjectMapperStandard.getJson(txCfg, true);
			System.out.println(json);
		}

	}

}
