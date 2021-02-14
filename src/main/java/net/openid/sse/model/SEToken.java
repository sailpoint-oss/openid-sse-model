package net.openid.sse.model;

import com.nimbusds.jwt.JWTClaimsSet;

import java.util.Map;

public class SEToken
{
    public static final String EVENTS_CLAIM = "events";

    private SEToken() {}

    public static void validate(JWTClaimsSet set) throws ValidationException {
        Object o = set.getClaim(EVENTS_CLAIM);
        if (null == o) {
            throw new ValidationException("SETs must contain an events member.");
        }
        if (!(o instanceof Map)) {
            throw new ValidationException("SET events member must be a Map.");
        }
        // FIXME this is where I leave off
        // SSEvent evt = new SSEvent((HashMap<String, Object>) o);
        // evt.validate() here when it's ready
    }
}
