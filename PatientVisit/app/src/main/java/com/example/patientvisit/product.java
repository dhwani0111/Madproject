package com.example.patientvisit;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class product implements Serializable {
    @Exclude private String id;
    String address,age,gender,medical_description,name,phone_no,starting_date,date;
    String cost,total_amount;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public product() {

    }

    public product(String id, String address, String age, String gender, String medical_description, String name, String phone_no, String starting_date, String date, String cost, String total_amount) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medical_description = medical_description;
        this.name = name;
        this.phone_no = phone_no;
        this.starting_date = starting_date;
        this.date = date;
        this.cost = cost;
        this.total_amount = total_amount;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
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

    public String getDate() {
        return date;
    }

    public String getCost() {
        return cost;
    }

    public String getTotal_amount() {
        return total_amount;
    }
}