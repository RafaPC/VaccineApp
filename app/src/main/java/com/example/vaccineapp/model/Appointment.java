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

    public String getDateString() {
        return date.toString("dd - MMM - YYYY");
    }

    public String getTimeString(){
        return date.toString("hh:mm aa");
    }

    public LocalDateTime getDate() {
        return date;
    }

    //CAMBIAR EL EQUALS

    public Profile getProfile() {
        for (int i = 0; i < ProfilesManager.profiles.size(); i++) {
            Profile profile = ProfilesManager.profiles.get(i);
            for (int j = 0; j < profile.getAppointments().size(); j++) {
                if(this.equals(profile.getAppointments().get(j))){
                    return profile;
                }
            }
        }
        return null;
    }

    public ArrayList<Service> getServices(){
        return this.services;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }else{
            return false;
        }
    }

    public boolean alreadyExists(){
        boolean exists = false;
        for(Profile profile: ProfilesManager.profiles){
            for(Appointment appointment: profile.getAppointments()){
                if(this.equals(appointment)){

               }
            }
        }
        return exists;
    }

    public int getServicesCost(){
        int cost = 0;

        for(Service service : this.services){
            cost += service.getPrice();
        }

        return cost;
    }
}
