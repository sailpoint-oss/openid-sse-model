package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

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

    public static class Builder {

        private static final StreamConfiguration sc = new StreamConfiguration();

        public Builder issuer(final String iss) {
            sc.put(ISSUER_MEMBER, iss);
            return this;
        }

        public Builder audience(final String aud) {
            sc.put(AUDIENCE_MEMBER, aud);
            return this;
        }

        public Builder audience(final List<String> aud) {
            sc.put(AUDIENCE_MEMBER, aud);
            return this;
        }

        public Builder eventsSupported(final List<String> events) {
            sc.put(EVENTS_SUPPORTED_MEMBER, events);
            return this;
        }

        public Builder eventsRequested(final List<String> events) {
            sc.put(EVENTS_REQUESTED_MEMBER, events);
            return this;
        }

        public Builder eventsDelivered(final List<String> events) {
            sc.put(EVENTS_DELIVERED_MEMBER, events);
            return this;
        }

        public Builder delivery(final JSONObject config) {
            sc.put(DELIVERY_MEMBER, config);
            return this;
        }

        public Builder minVerificationInterval(final int interval) {
            sc.put(MIN_VERIFICATION_INTERVAL_MEMBER, interval);
            return this;
        }

        public Builder format(final String format) {
            sc.put(FORMAT_MEMBER, format);
            return this;
        }

        public StreamConfiguration build() {
            return sc;
        }
    }

}
