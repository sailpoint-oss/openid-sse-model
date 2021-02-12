package net.openid.sse.model;

public class CAEPAssuranceLevelChange extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        private static final String CURRENT_LEVEL    = "current_level";
        private static final String PREVIOUS_LEVEL   = "previous_level";
        private static final String CHANGE_DIRECTION = "change_direction";

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_ASSURANCE_LEVEL_CHANGE);
        }

        public Builder currentLevel(final NISTAuthenticatorAssuranceLevel level) {
            super.members.put(CURRENT_LEVEL, level.toString());
            return this;
        }

        public Builder previousLevel(final NISTAuthenticatorAssuranceLevel level) {
            super.members.put(PREVIOUS_LEVEL, level.toString());
            return this;
        }

        public Builder changeDirection(final AssuranceLevelChangeDirection direction) {
            super.members.put(CHANGE_DIRECTION, direction.toString());
            return this;
        }

    }
}
