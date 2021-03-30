/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Commonly used SSE stream states for SSE/RISC/CAEP events.
 * <p>
 * A Transmitter MAY decide to enable, pause or disable updates about a
 * SPAG independently of an update request from a Receiver.  If a
 * Transmitter decides to start or stop events for a SPAG then the
 * Transmitter MUST do the following according to the status of the
 * stream. If the stream is:
 * <p>
 * Enabled  the Transmitter MUST send a SPAG stream updated
 * (Section 8.1.5) event respectively to the Receiver within the Event Stream.
 * Paused  the Transmitter SHOULD send SPAG stream updated
 * (Section 8.1.5) after the Event Stream is re-started.
 * Disabled  the Transmitter MAY send SPAG stream updated
 * (Section 8.1.5) after the Event Stream is re-enabled.
 */
public enum StreamStates {

    ENABLED("enabled"),
    PAUSED("paused"),
    DISABLED("disabled");

    private static final Map<String, StreamStates> BY_NAME = new HashMap<>();

    static {
        for (StreamStates t : values()) {
            BY_NAME.put(t.name, t);
        }
    }

    private final String name;

    StreamStates(final String s) {
        name = s;
    }

    public static StreamStates valueOfLabel(final String name) {
        return BY_NAME.get(name);
    }

    public static boolean contains(final String name) {
        return BY_NAME.containsKey(name);
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
