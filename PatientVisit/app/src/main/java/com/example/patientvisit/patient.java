package com.example.patientvisit;

public class patient {
    private String date,name,address,age,phone,cost,medical;

    public patient(){

    }
    public patient(String name,String medical,String address,String age,String phone,String cost,String date){
        this.name=name;
        this.medical=medical;
        this.address=address;
        this.phone=phone;
        this.cost=cost;
        this.age=age;
        //this.gender=gender;
        this.date=date;
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

    public String getDate() {
        return date;
    }

    public String getMedical() {
        return medical;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
