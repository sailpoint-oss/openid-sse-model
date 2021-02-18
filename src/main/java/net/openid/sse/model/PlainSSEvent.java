package net.openid.sse.model;

public class PlainSSEvent extends CAEPBaseEvent {

    public static class Builder extends SSEvent.Builder<PlainSSEvent, PlainSSEvent.Builder> {

        protected PlainSSEvent createObj() {return new PlainSSEvent();}
        protected PlainSSEvent.Builder getThis() { return this; }

    }
}