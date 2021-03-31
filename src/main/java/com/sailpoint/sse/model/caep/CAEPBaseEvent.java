/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model.caep;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.sailpoint.sse.model.SSEvent;
import com.sailpoint.sse.model.SSEventTypes;
import com.sailpoint.sse.model.ValidationException;

public abstract class CAEPBaseEvent extends SSEvent {
    private static final String EVENT_TIMESTAMP_MEMBER = "event_timestamp";
    private static final String INITIATING_ENTITY_MEMBER = "initiating_entity";
    private static final String REASON_ADMIN_MEMBER = "reason_admin";
    private static final String REASON_USER_MEMBER = "reason_user";

    protected CAEPBaseEvent() { }

    public JSONObject getEventMembers() throws ValidationException {
        final SSEventTypes eventType = getEventType();
        if (null == eventType) {
            /* Unknown event type, not instantiated via a normal constructor. */
            throw new ValidationException("CAEP Events must set eventType in their constructor");
        }

        JSONObject members = (JSONObject) get(eventType.toString());
        if (null == members) {
            throw new ValidationException("CAEP Events must have a container Map whose key is the event type URI");
        }
        return members;
    }

    public void validateEventTimestamp() throws ValidationException {
        JSONObject members = getEventMembers();

        Object eventTimestamp = members.get(EVENT_TIMESTAMP_MEMBER);
        if (null == eventTimestamp) {
            throw new ValidationException("CAEP Events must have an event_timestamp member");
        }

        if (!(eventTimestamp instanceof Long)) {
            throw new ValidationException("CAEP Events event_timestamp must be of type Long.");
        }
        Long eventTimestampL = (Long) eventTimestamp;
        if (eventTimestampL < 0) {
            throw new ValidationException("CAEP Events event_timestamp must be > 0.");
        }
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        validateEventTimestamp();
    }

    public abstract static class Builder<T extends CAEPBaseEvent, B extends CAEPBaseEvent.Builder<T, B>>
            extends SSEvent.Builder<T, B> {

        protected Builder(final SSEventTypes eventType) {
            super(eventType);
        }

        // Timestamp in milliseconds from epoch
        public B eventTimestamp(final long eventTimestamp) {
            members.put(EVENT_TIMESTAMP_MEMBER, eventTimestamp);
            return thisObj;
        }

        public B initiatingEntity(final CAEPInitiatingEntity entity) {
            members.put(INITIATING_ENTITY_MEMBER, entity.toString());
            return thisObj;
        }

        public B reasonAdmin(final String s) {
            members.put(REASON_ADMIN_MEMBER, s);
            return thisObj;
        }

        public B reasonUser(final String s) {
            members.put(REASON_USER_MEMBER, s);
            return thisObj;
        }

    }


}
