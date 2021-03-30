/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.Map;
import java.util.Objects;

/**
 * A Shared Signals Event (SSE Event).
 *
 * @author adam.hampton
 * @author matt.domsch
 */
public abstract class SSEvent extends JSONObject {

    private static final String SUBJECT_MEMBER = "subject";
    private static final String STATUS_MEMBER = "status";
    private static final String REASON_MEMBER = "reason";
    private static final String PROPERTIES_MEMBER = "properties";

    private SSEventTypes eventType;

    protected SSEvent() {
    }

    public SSEventTypes getEventType() {
        return eventType;
    }

    public void setEventType(final SSEventTypes eventType) {
        this.eventType = eventType;
    }

    private void validateEventTypeName() throws ValidationException {
        for (String k : this.keySet()) {
            if (SSEventTypes.contains(k)) {
                return;
            }
        }
        throw new ValidationException("SSEvent eventTypeName not in SSEventTypes.");
    }

    private void validateSubjectPresent() throws ValidationException {
        if (null == eventType) {
            /* Unknown event type, not instantiated via a normal constructor. */
            return;
        }
        JSONObject members = (JSONObject) get(eventType.toString());
        if (null == members) {
            throw new ValidationException("SSE Events must have a container Map whose key is the event type URI");
        }

        if (!members.containsKey(SUBJECT_MEMBER)) {
            throw new ValidationException("SSE Events must include subject member.");
        }
    }

    public Object getMember(final String member) {
        JSONObject members = (JSONObject) get(eventType.toString());
        if (null == members) {
            return null;
        }

        if (!members.containsKey(member)) {
            return null;
        }
        return members.get(member);
    }

    public final SubjectIdentifier getSubjectIdentifier() {
        return (SubjectIdentifier) getMember(SUBJECT_MEMBER);
    }

    public final String getStatus() {
        return (String) getMember(STATUS_MEMBER);
    }

    public void validate() throws ValidationException {
        validateEventTypeName();
        validateSubjectPresent();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        SSEvent event = (SSEvent) obj;
        return eventType.equals(event.getEventType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this, eventType);
    }

    protected abstract static class Builder<T extends SSEvent, B extends Builder<T, B>> {

        protected final T members;
        protected final B thisObj;
        private SSEventTypes eventType;

        protected Builder() {
            members = createObj();
            thisObj = getThis();
        }

        protected Builder(final SSEventTypes eventType) {
            members = createObj();
            thisObj = getThis();
            this.eventType(eventType);
        }

        public T build() {
            T t = createObj();
            t.setEventType(eventType);
            t.put(eventType.toString(), members);
            return t;
        }

        protected abstract T createObj();

        protected abstract B getThis();

        public B eventType(final SSEventTypes eventType) {
            this.eventType = eventType;
            return thisObj;
        }

        public B subject(final Map<String, Object> sub) {
            members.put(SUBJECT_MEMBER, sub);
            return thisObj;
        }

        public B status(final String status) {
            members.put(STATUS_MEMBER, status);
            return thisObj;
        }

        public B reason(final String reason) {
            members.put(REASON_MEMBER, reason);
            return thisObj;
        }

        public B properties(final Map<String, Object> properties) {
            members.put(PROPERTIES_MEMBER, properties);
            return thisObj;
        }

        public B member(final String key, final Object o) {
            members.put(key, o);
            return thisObj;
        }
    }
}



