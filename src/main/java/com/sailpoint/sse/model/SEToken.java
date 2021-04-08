/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jwt.JWTClaimsSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

public class SEToken {
    public static final String EVENTS_CLAIM = "events";
    private static final String TYP_CLAIM = "typ";
    private static final String EXP_CLAIM = "exp";
    private static final String TYP_VALUE = "secevent+jwt";

    private SEToken() {
    }

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
        Map<String, Object> o = set.getJSONObjectClaim(EVENTS_CLAIM);

        if (null == o) {
            throw new ValidationException("SETs must contain an events member.");
        }

        if (o.size() > 1) {
            throw new ValidationException("SET events member must contain exactly one event.");
        }
    }

    public static void validate(final JWTClaimsSet set) throws ParseException, ValidationException {
        validateTyp(set);
        validateExp(set);
        validateEventsClaim(set);
    }

    // Handle recursive nature of SubjectIdentifiers
    private static SubjectIdentifier convertSubjects(final JSONObject subjectJO) {
        if (null == subjectJO) { return null; }

        SubjectIdentifier subj = new SubjectIdentifier();
        subj.merge(subjectJO);

        // Override child SIs with specific object types
        for (Map.Entry<String,Object> entry : subjectJO.entrySet()) {
            String k = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JSONObject) {
                subj.put(k, convertSubjects((JSONObject) value));
            }
        }
        return subj;
    }

    /**
     * Parse a JSON String into a fully fleshed sse.model class hierarchy
     * @param jsonString - representing a JSON object "{ ... }"
     * @return a JWTClaimsSet wherein all members have been converted to sse.model classes
     * @throws ParseException when JSONObject parsing fails
     * @throws ValidationException when SSE validation rules fail
     */

    public static JWTClaimsSet parse(final String jsonString) throws ParseException, ValidationException {
        SSEvent event;
        Constructor<? extends SSEvent> ctor;
        Class<? extends SSEvent> cls;

        JWTClaimsSet set = JWTClaimsSet.parse(jsonString);
        SEToken.validate(set);

        JSONObject eventsClaim = new JSONObject(set.getJSONObjectClaim(SEToken.EVENTS_CLAIM));
        // There can be exactly one event present after the above validation
        String eventName = eventsClaim.keySet().iterator().next();
        SSEventTypes eventType = SSEventTypes.enumByName(eventName);

        if (null != eventType) {
            cls = eventType.getCls();
        } else {
            cls = NonstandardSSEvent.class;
        }

        try {
            ctor = cls.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new ValidationException("SEToken Parser cannot find a constructor() for " + cls.getName());
        }

        // event is an instance of the specific event type by this eventName URL
        try {
            event = ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ValidationException("SEToken Parser cannot instantiate matching class for event type" + eventName);
        }
        event.setEventType(eventType);
        event.setEventTypeName(eventName);

        // Convert the incoming event subject into a SubjectIdentifier hierarchy
        JSONObject eventDetailsJO = (JSONObject) eventsClaim.get(eventName);
        JSONObject subjectJO = (JSONObject) eventDetailsJO.get(SSEvent.SUBJECT_MEMBER);
        SubjectIdentifier subj = convertSubjects(subjectJO);
        eventDetailsJO.put(SSEvent.SUBJECT_MEMBER, subj);
        event.merge(eventsClaim);

        // From this point down, all members are base Java types (Strings, Longs) or more JSONObjects
        // so we shouldn't need to parse further.
        event.validate();

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder(set)
                .claim(SEToken.EVENTS_CLAIM, event);

        JWTClaimsSet newSet = builder.build();
        SEToken.validate(newSet);
        return newSet;
    }


}
