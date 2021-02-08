package sse.model;

import java.text.ParseException;
import java.util.Arrays;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import com.nimbusds.jose.shaded.json.JSONObject;

import org.junit.Test;

public class JsonRoundTripTest {

	public static final String EVENTS_CLAIM = "events";

	@Test()
	public void Test2() throws ParseException {
		SubjectIdentifier subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.ISSUER_SUBJECT_SUBJECT_IDENTIFIER_TYPE)
				.issuer("https://issuer.example.com/")
				.subject("abc1234")
				.build();

		SSEvent evt = new SSEvent.Builder()
				.subject(subj)
				.build();

		JSONObject eventType = new JSONObject();
		eventType.put(SSEventTypes.RISC_ACCOUNT_ENABLED.toString(), evt);

		JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://idp.example.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis() / 1000))
				.audience("636C69656E745F6964")
				.claim(EVENTS_CLAIM, eventType)
				.build();

		String json = set.toString();
		JWTClaimsSet setNew = JWTClaimsSet.parse(json);
	}

	@Test
	public void Test3() throws ParseException {
		// Reproduce Atul's draft text from `openid-sse-profile-draft2` page 7.
		SubjectIdentifier subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
				.subject("foo@example.com")
				.build();

		SSEvent evt = new SSEvent.Builder()
				.subject(subj)
				.ipAddress("123.45.67.89") // From CAEP example, ha!
				.build();

		JSONObject eventType = new JSONObject();
		eventType.put(SSEventTypes.CAEP_IPADDR_CHANGED.toString(), evt);

		JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://sp.example2.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis() / 1000))
				.audience("636C69656E745F6964")
				.claim(EVENTS_CLAIM, eventType)
				.build();

		String json = set.toString();
		JWTClaimsSet setNew = JWTClaimsSet.parse(json);
	}

	@Test()
	public void Test4() throws ParseException {
		SubjectIdentifier subj = new SubjectIdentifier.Builder()
				.subjectType(SubjectIdentifier.SPAG_SUBJECT_IDENTIFIER_TYPE)
				.spagID("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a")
				.build();

		SSEvent evt = new SSEvent.Builder()
				.subject(subj)
				.status("paused")
				.reason("License is not valid")
				.build();

		JSONObject eventType = new JSONObject();
		eventType.put(SSEventTypes.CAEP_STREAM_UPDATED.toString(), evt);

		JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://sp.example2.com/")
				.jwtID("756E69717565206964656E746966696572")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis() / 1000))
				.audience("636C69656E745F6964")
				.claim(EVENTS_CLAIM, eventType)
				.build();

		String json = set.toString();
		JWTClaimsSet setNew = JWTClaimsSet.parse(json);
	}


}
