package net.openid.sse.model;

import java.text.ParseException;
import java.util.Arrays;

import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.junit.Test;

public class MultiEventInSetTest {

	@Test()
	public void MultiEventSETTest() throws ParseException {
		SSEvent urnEvent = new SSEvent.Builder()
				.eventType("urn:ietf:params:scim:event:passwordReset")
				.id("44f6142df96bd6ab61e7521d9")
				.build();

		SSEvent urlEvent = new SSEvent.Builder()
				.eventType("https://example.com/scim/event/passwordResetExt")
				.claim("resetAttempts", 5)
				.build();

		urlEvent.merge(urnEvent);

		JWTClaimsSet set = new JWTClaimsSet.Builder()
				.issuer("https://scim.example.com")
				.jwtID("3d0c3cf797584bd193bd0fb1bd4e7d30")
				.issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
				.audience(Arrays.asList("https://jhub.example.com/Feeds/98d52461fa5bbc879593b7754",
						"https://jhub.example.com/Feeds/5d7604516b1d08641d7676ee7"))
				.subject("https://scim.example.com/Users/44f6142df96bd6ab61e7521d9")
				.claim("events", urlEvent)
				.build();

		String json = set.toString();
		JWTClaimsSet setNew = JWTClaimsSet.parse(json);
	}
}
