/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class Student implements Serializable, Comparable<Student>{
    private String ID;
    private String fullName;
    private boolean gender;
    private String dob;
    private String phoneNumber;

    public Student() {
    }

    public Student(String ID, String fullName, boolean gender, String dob, String phoneNumber) {
        this.ID = ID;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Student o) {
        return this.fullName.compareTo(o.fullName);
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-6s | %-13s | %-11s |", 
                this.ID, this.fullName, (this.gender?"Male":"Female"),this.dob, this.phoneNumber);
    }
}
