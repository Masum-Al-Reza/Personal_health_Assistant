package com.example.finalyear.pojos;

public class Docotor_User_pojos {
    private String Doctor_profileID;
    private String Doctor_profilename;
    private String Doctor_number;
    private String Doctor_address;
    private  String Doctor_type;
    public Docotor_User_pojos() {
    }

    public Docotor_User_pojos(String doctor_profileID, String doctor_profilename, String doctor_number, String doctor_address, String doctor_type) {
        Doctor_profileID = doctor_profileID;
        Doctor_profilename = doctor_profilename;
        Doctor_number = doctor_number;
        Doctor_address = doctor_address;
        Doctor_type = doctor_type;
    }

    public String getDoctor_profileID() {
        return Doctor_profileID;
    }

    public void setDoctor_profileID(String doctor_profileID) {
        Doctor_profileID = doctor_profileID;
    }

    public String getDoctor_profilename() {
        return Doctor_profilename;
    }

    public void setDoctor_profilename(String doctor_profilename) {
        Doctor_profilename = doctor_profilename;
    }

    public String getDoctor_number() {
        return Doctor_number;
    }

    public void setDoctor_number(String doctor_number) {
        Doctor_number = doctor_number;
    }

    public String getDoctor_address() {
        return Doctor_address;
    }

    public void setDoctor_address(String doctor_address) {
        Doctor_address = doctor_address;
    }

    public String getDoctor_type() {
        return Doctor_type;
    }

    public void setDoctor_type(String doctor_type) {
        Doctor_type = doctor_type;
    }
}
