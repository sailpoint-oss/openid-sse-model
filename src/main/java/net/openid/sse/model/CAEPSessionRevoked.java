package net.openid.sse.model;

// TODO: create more CAEP event-specific classes

public class CAEPSessionRevoked extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_SESSION_REVOKED);
        }

    }
}
