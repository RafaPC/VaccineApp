package com.example.vaccineapp.model;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class Appointment {
    private String reason;
    private String hospital; //This could be an id to identify the hospital
    private String doctor; //The same for this field too
    private org.joda.time.LocalDateTime date;
    private ArrayList<Service> services;

    public Appointment(String reason, String hospital, String doctor, org.joda.time.LocalDateTime date, ArrayList<Service> services) {
        this.reason = reason;
        this.hospital = hospital;
        this.doctor = doctor;
        this.date = date;
        this.services = services;
    }

    public String getReason() {
        return reason;
    }

    public String getHospital() {
        return hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDateString(){
        return "" + date.getHourOfDay();
    }

    public LocalDateTime getDate(){
        return date;
    }


    public boolean equals(int year, int month, int day){
        if(date.getYear() == year && date.getMonthOfYear() == month && date.getDayOfMonth() == day){
            return true;
        }else{
            return false;
        }
    }
}
