package net.openid.sse.model;

public class CAEPTokenClaimsChange extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_TOKEN_CLAIMS_CHANGE);
        }
    }
}
