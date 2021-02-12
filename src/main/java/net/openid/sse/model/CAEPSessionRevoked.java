package net.openid.sse.model;

public class CAEPSessionRevoked extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_SESSION_REVOKED);
        }

    }
}
