package net.openid.sse.model;

public class CAEPDeviceComplianceChange extends CAEPBaseEvent {

    public static class Builder extends CAEPBaseEvent.Builder {

        private static final String PREVIOUS_STATUS = "previous_status";
        private static final String CURRENT_STATUS = "current_status";

        public Builder() {
            super();
            super.eventType(SSEventTypes.CAEP_DEVICE_COMPLIANCE_CHANGE);
        }

        public Builder previousStatus(final String status) {
            super.members.put(PREVIOUS_STATUS, status);
            return this;
        }

        public Builder currentStatus(final String status) {
            super.members.put(CURRENT_STATUS, status);
            return this;
        }
    }
}

