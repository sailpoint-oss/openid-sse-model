package sse.scaffolding;

import java.text.ParseException;
import java.util.Arrays;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sse.model.SSEvent;
import sse.model.SSEventTypes;
import sse.model.SubjectIdentifier;
import sse.model.TransmitterConfig;

public class JsonRoundTrip {
	
	static final Logger log = LoggerFactory.getLogger(JsonRoundTrip.class);

	public static void main(String[] args) {

		System.err.println("");
		System.out.println("JSON Round Trip Scaffolding");

		{ 
			System.out.println("# Draft Figure 1, 'SET Containing a SSE Event with an Email Subject Identifier', page 6 top:");
			JSONObject subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
				.subject("foo@example.com")
				.build();

			JSONObject evt = new SSEvent.Builder()
				.subject(subj)
				.build();

			JSONObject eventType = new JSONObject();
			eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

			JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://idp.example.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
				.audience("636C69656E745F6964")
				.claim("events", eventType)
				.build();

			String json = set.toString();
			System.out.println(json);
			
			try {
				JWTClaimsSet setNew = JWTClaimsSet.parse(json);
			} catch (ParseException e) {
				log.error("ParseException failure de-serializing JSON {}", json, e);
			}
		}

		{ 
			System.out.println("# Draft Figure 2, 'Example SET', page 6 bottom:");
			JSONObject subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
				.issuer("https://issuer.example.com/")
				.subject("abc1234")
				.build();

			JSONObject evt = new SSEvent.Builder()
				.subject(subj)
				.build();

			JSONObject eventType = new JSONObject();
			eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

			JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://idp.example.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
				.audience("636C69656E745F6964")
				.claim("events", eventType)
				.build();

			String json = set.toString();
			System.out.println(json);

			try {
				JWTClaimsSet setNew = JWTClaimsSet.parse(json);
			} catch (ParseException e) {
				log.error("ParseException failure de-serializing JSON {}", json, e);
			}
		}

		{
			// Reproduce Atul's draft text from `openid-sse-profile-draft2` page 7.
			System.out.println("# Draft Figure 3, 'Example SET', page 7 top:");
			JSONObject subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
				.subject("foo@example.com")
				.build();

			JSONObject evt = new SSEvent.Builder()
				.subject(subj)
				.ipAddress("123.45.67.89") // From CAEP example, ha!
				.build();

			JSONObject eventType = new JSONObject();
			eventType.put(SSEventTypes.CAEP_IPADDR_CHANGED.toString(), evt);

			JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://sp.example2.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
				.audience("636C69656E745F6964")
				.claim("events", eventType)
				.build();


			String json = set.toString();
			System.out.println(json);

			try {
				JWTClaimsSet setNew = JWTClaimsSet.parse(json);
			} catch (ParseException e) {
				log.error("ParseException failure de-serializing JSON {}", json, e);
			}
		}

		{
			System.out.println("# Draft Figure 9, 'SET Containing a SSE Event with a Subject and a Property Claim'");
			JSONObject subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.SPAG_SUBJECT_IDENTIFIER_TYPE)
				.spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
				.build();

			JSONObject evt = new SSEvent.Builder()
				.subject(subj)
				.status("paused")
				.reason("License is not valid")
				.build();

			JSONObject eventType = new JSONObject();
			eventType.put(SSEventTypes.CAEP_STREAM_UPDATED.toString(), evt);

			JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://sp.example2.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
				.audience("636C69656E745F6964")
				.claim("events", eventType)
				.build();

			String json = set.toString();
			System.out.println(json);

			try {
				JWTClaimsSet setNew = JWTClaimsSet.parse(json);
			} catch (ParseException e) {
				log.error("ParseException failure de-serializing JSON {}", json, e);
			}
		}

		{
			System.out.println("TransmitterConfig JSON example:");
			String iss = "https://ssedemo.example.com";

			JSONObject txCfg = new TransmitterConfig.Builder()
				.issuer(iss)
				.jwks_uri(iss + "/jwks")
				.supported_versions(Arrays.asList("1.0"))
				.configuration_endpoint(iss + "/config")
				.status_endpoint(iss + "/status")
				.add_subject_endpoint(iss + "/addSubject")
				.remove_subject_endpoint(iss + "/removeSubject")
				.verification_endpoint(iss + "/verify")
				.delivery_methods_supported(Arrays.asList("https"))
				.build();

			String json  = txCfg.toString();
			System.out.println(json);
			
		}
	}

}
