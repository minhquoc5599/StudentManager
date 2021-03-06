package com.example.studentmanager.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String mssv;
    private String name;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", mssv='" + mssv + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(String mssv, String name, String email, String phoneNumber) {
        this.mssv = mssv;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmssv() {
        return mssv;
    }

    public void setmssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
