package com.example.finalyear.pojos;

public class Callories_pojos {
    private String eventID;
    private String Dietname;
    private String Diet_type;
    private int budget;
    private String Callories_date;
    private String last_Callories_date;

    public Callories_pojos() {
        //required by Firebase
    }

    public Callories_pojos(String eventID, String Dietname, String Diet_type, int budget, String Callories_date,String last_Callories_date) {
        this.eventID = eventID;
        this.Dietname = Dietname;
        this.Diet_type = Diet_type;
        this.budget = budget;
        this.last_Callories_date=last_Callories_date;
        this.Callories_date = Callories_date;
    }

    public String getEventID() {
        return eventID;
    }

    public String getLast_Callories_date() {
        return last_Callories_date;
    }

    public void setLast_Callories_date(String last_Callories_date) {
        this.last_Callories_date = last_Callories_date;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getDietname() {
        return Dietname;
    }

    public void setDietname(String dietname) {
        this.Dietname = dietname;
    }

    public String getDiet_type() {
        return Diet_type;
    }

    public void setDiet_type(String diet_type) {
        this.Diet_type = diet_type;
    }



    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getCallories_date() {
        return Callories_date;
    }

    public void setCallories_date(String callories_date) {
        this.Callories_date = callories_date;
    }
}
