package com.example.vaccineapp;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int color;
    private String birthdate;
    private ArrayList<String> information;
    private ArrayList<InfoElement> reminders;

    public Profile(String name, int color, String birthdate) {
        this.name = name;
        this.color = color;
        this.birthdate = birthdate;
        this.information = new ArrayList<>();
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public ArrayList<String> getInformation() {
        return information;
    }

    public void addInformation(String information) {
        this.information.add(information);
    }


    public void addReminder(InfoElement reminder) {
        this.reminders.add(reminder);
    }

    public ArrayList<InfoElement> getReminders(){
        return this.reminders;
    }
}
