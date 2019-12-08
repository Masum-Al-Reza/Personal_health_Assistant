package com.example.finalyear.pojos;

import java.util.ArrayList;

public class Medicine_User_pojos {
    private String Medicine_profileID;
    private String Medicine_profilename;
    private String Medicine_date_limit;
    private  String medic_currentdate;
    private ArrayList<String> medicine_time;


    private String Medicine_type;

    public Medicine_User_pojos() {
    }

    public String getMedicine_profileID() {
        return Medicine_profileID;
    }

    public void setMedicine_profileID(String medicine_profileID) {
        Medicine_profileID = medicine_profileID;
    }

    public String getMedicine_profilename() {
        return Medicine_profilename;
    }

    public void setMedicine_profilename(String medicine_profilename) {
        Medicine_profilename = medicine_profilename;
    }

    public String getMedicine_date_limit() {
        return Medicine_date_limit;
    }

    public void setMedicine_date_limit(String medicine_date_limit) {
        Medicine_date_limit = medicine_date_limit;
    }



    public String getMedicine_type() {
        return Medicine_type;
    }

    public ArrayList<String> getMedicine_time() {

        return medicine_time;
    }

    public String getMedic_currentdate() {
        return medic_currentdate;
    }

    public void setMedic_currentdate(String medic_currentdate) {
        this.medic_currentdate = medic_currentdate;
    }

    public void setMedicine_time(ArrayList<String> medicine_time) {
        this.medicine_time = medicine_time;
    }

    public void setMedicine_type(String medicine_type) {
        Medicine_type = medicine_type;
    }

    public Medicine_User_pojos(String medicine_profileID, String medicine_profilename, String medicine_date_limit, ArrayList<String> medicine_time, String medicine_type,String medic_currentdate)
    {
        Medicine_profileID = medicine_profileID;
        Medicine_profilename = medicine_profilename;
        Medicine_date_limit = medicine_date_limit;
        this.medicine_time = medicine_time;
        this.medic_currentdate = medic_currentdate;
        Medicine_type = medicine_type;
    }
}
