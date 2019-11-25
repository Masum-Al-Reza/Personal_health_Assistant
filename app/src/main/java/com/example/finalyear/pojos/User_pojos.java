package com.example.finalyear.pojos;

public class User_pojos {
    private String profileID;
    private  String profilename;
    private String  number;
    private  String address;
    private  int age;

    public User_pojos() {
    }

    public User_pojos(String profileID, String profilename, String number, String address, int age) {
        this.profileID = profileID;
        this.profilename = profilename;
        this.number = number;
        this.address = address;
        this.age = age;
    }

    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
