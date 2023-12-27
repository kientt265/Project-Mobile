package com.example.salon.Domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class User implements Serializable {

    private String ID;
    private String name;
    private String mobile;
    private String address;
    private String dob;
    public static FirebaseAuth mAuth;
    public static FirebaseDatabase Database = FirebaseDatabase.getInstance();


    public User()
    {

    }
    public User( String ID, String name, String mobile, String address, String dob){
        this.ID=ID;
        this.name=name;
        this.mobile=mobile;
        this.address=address;
        this.dob=dob;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
