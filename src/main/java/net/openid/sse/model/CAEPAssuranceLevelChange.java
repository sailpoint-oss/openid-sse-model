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

        public Builder currentLevel(final String level) {
            super.members.put(CURRENT_LEVEL, level);
            return this;
        }

        public Builder previousLevel(final String level) {
            super.members.put(PREVIOUS_LEVEL, level);
            return this;
        }

        public Builder changeDirection(final String direction) {
            super.members.put(CHANGE_DIRECTION, direction);
            return this;
        }

    }
}
