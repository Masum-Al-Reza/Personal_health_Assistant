package com.example.finalyear.pojos;

public class Event_consume_pojos {
    private String consumeid;
    private String eventId;
    private String Consumename;
    private int callories;
    private String consumeDateTime;

    public Event_consume_pojos() {
    }

    public Event_consume_pojos(String consumeid, String eventId, String consumename, int callories, String consumeDateTime) {
        this.consumeid = consumeid;
        this.eventId = eventId;
        Consumename = consumename;
        this.callories = callories;
        this.consumeDateTime = consumeDateTime;
    }

    public String getConsumeid() {
        return consumeid;
    }

    public void setConsumeid(String consumeid) {
        this.consumeid = consumeid;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getConsumename() {
        return Consumename;
    }

    public void setConsumename(String consumename) {
        Consumename = consumename;
    }

    public int getCallories() {
        return callories;
    }

    public void setCallories(int callories) {
        this.callories = callories;
    }

    public String getConsumeDateTime() {
        return consumeDateTime;
    }

    public void setConsumeDateTime(String consumeDateTime) {
        this.consumeDateTime = consumeDateTime;
    }
}
