package com.example.finalyear.pojos;

public class User_pojos {
    private String profileID;
    private  String profilename;
    private String  number;
    private String  gender;
    private float Weight;
    private float height;
    private  int age;

    public User_pojos() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User_pojos(String profileID, String profilename, String number, String gender, float weight, float height, int age) {
        this.profileID = profileID;
        this.profilename = profilename;
        this.number = number;
        this.Weight = weight;
        this.gender=gender;
        this.age = age;
        this.height=height;
    }


    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
