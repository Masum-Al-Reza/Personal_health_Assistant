package com.example.finalyear.pojos;

public class Water_pojos  {
    private String waterid;
    private int initialwater=1200;
    private int consumewater;
    private  String currentdate;

    public Water_pojos(String waterid, int initialwater, int consumewater, String currentdate) {
        this.waterid = waterid;
        this.initialwater = initialwater;
        this.consumewater = consumewater;
        this.currentdate = currentdate;
    }

    public String getWaterid() {
        return waterid;
    }

    public void setWaterid(String waterid) {
        this.waterid = waterid;
    }

    public int getInitialwater() {
        return initialwater;
    }

    public void setInitialwater(int initialwater) {
        this.initialwater = initialwater;
    }

    public int getConsumewater() {
        return consumewater;
    }

    public void setConsumewater(int consumewater) {
        this.consumewater = consumewater;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }
}
