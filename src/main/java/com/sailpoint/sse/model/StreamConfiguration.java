/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StreamConfiguration extends JSONObject {

    private static final String ISSUER_MEMBER = "iss";
    private static final String AUDIENCE_MEMBER = "aud";
    private static final String EVENTS_SUPPORTED_MEMBER = "events_supported";
    private static final String EVENTS_REQUESTED_MEMBER = "events_requested";
    private static final String EVENTS_DELIVERED_MEMBER = "events_delivered";
    private static final String DELIVERY_MEMBER = "delivery";
    private static final String MIN_VERIFICATION_INTERVAL_MEMBER = "min_verification_interval";
    private static final String FORMAT_MEMBER = "format";

    private static final HashSet<String> readOnlyMembers = new HashSet<>(Arrays.asList(
            ISSUER_MEMBER,
            AUDIENCE_MEMBER,
            EVENTS_SUPPORTED_MEMBER,
            EVENTS_DELIVERED_MEMBER,
            MIN_VERIFICATION_INTERVAL_MEMBER));

    @Override
    public Object put(String key, Object value) throws UnsupportedOperationException {
        if (readOnlyMembers.contains(key)) {
            throw new UnsupportedOperationException(String.format("StreamConfiguration member %s is read-only", key));
        }
        return super.put(key, value);
    }

    protected Object superPut(String key, Object value) {
        return super.put(key, value);
    }

    public static class Builder {

        private static final StreamConfiguration sc = new StreamConfiguration();

        public Builder issuer(final String iss) {
            sc.superPut(ISSUER_MEMBER, iss);
            return this;
        }

        public Builder audience(final String aud) {
            sc.superPut(AUDIENCE_MEMBER, aud);
            return this;
        }

        public Builder audience(final List<String> aud) {
            sc.superPut(AUDIENCE_MEMBER, aud);
            return this;
        }

        public Builder eventsSupported(final List<String> events) {
            sc.superPut(EVENTS_SUPPORTED_MEMBER, events);
            return this;
        }

        public Builder eventsRequested(final List<String> events) {
            sc.superPut(EVENTS_REQUESTED_MEMBER, events);
            return this;
        }

        public Builder eventsDelivered(final List<String> events) {
            sc.superPut(EVENTS_DELIVERED_MEMBER, events);
            return this;
        }

        public Builder delivery(final JSONObject config) {
            sc.superPut(DELIVERY_MEMBER, config);
            return this;
        }

        public Builder minVerificationInterval(final int interval) {
            sc.superPut(MIN_VERIFICATION_INTERVAL_MEMBER, interval);
            return this;
        }

        public Builder format(final String format) {
            sc.superPut(FORMAT_MEMBER, format);
            return this;
        }

        public StreamConfiguration build() {
            return sc;
        }
    }


}
