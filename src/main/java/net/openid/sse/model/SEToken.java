/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;

public class SEToken
{
    public static final String EVENTS_CLAIM = "events";
    private static final String TYP_CLAIM = "typ";
    private static final String EXP_CLAIM = "exp";
    private static final String TYP_VALUE = "secevent+jwt";

    private SEToken() {}

    /* openid-sse-profile-1_0.txt section 11.1.4 */
    private static void validateTyp(final JWTClaimsSet set) throws ParseException, ValidationException {
        String typ;
        typ = set.getStringClaim(TYP_CLAIM);

        if (null == typ) {
            /* OK to be missing */
            return;
        }

        if (!TYP_VALUE.equals(typ)) {
            throw new ValidationException("SET events claim typ value must be " + TYP_VALUE + ".");
        }
    }

    /* openid-sse-profile-1_0.txt section 11.1.5 */
    private static void validateExp(final JWTClaimsSet set) throws ValidationException {
        Object exp;
        exp = set.getClaim(EXP_CLAIM);
        if (null != exp) {
            throw new ValidationException("SET events must not have an exp claim.");
        }
    }

    /* openid-sse-profile-1_0.txt section 11.1.7 */
    private static void validateEventsClaim(final JWTClaimsSet set) throws ParseException, ValidationException {
        Object o;
        o = set.getJSONObjectClaim(EVENTS_CLAIM);

        if (null == o) {
            throw new ValidationException("SETs must contain an events member.");
        }
    }

    public static void validate(final JWTClaimsSet set) throws ParseException, ValidationException {
        validateTyp(set);
        validateExp(set);
        validateEventsClaim(set);
    }
}
