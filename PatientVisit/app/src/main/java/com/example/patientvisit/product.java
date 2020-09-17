package com.example.patientvisit;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class product implements Serializable {
    @Exclude private String id;
    String address,age,cost,gender,medical_description,name,phone_no,starting_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public product() {
    }

    public product(String address, String age, String cost, String gender, String medical_description, String name, String phone_no, String starting_date) {
        this.address = address;
        this.age = age;
        this.cost = cost;
        this.gender = gender;
        this.medical_description = medical_description;
        this.name = name;
        this.phone_no = phone_no;
        this.starting_date = starting_date;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getCost() {
        return cost;
    }

    public String getGender() {
        return gender;
    }

    public String getMedical_description() {
        return medical_description;
    }

    public String getName() {
        return name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getStarting_date() {
        return starting_date;
    }
}