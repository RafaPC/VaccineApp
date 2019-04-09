package com.example.vaccineapp;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int color;
    private String birthdate;
    private ArrayList<String> information;
    private ArrayList<Reminder> reminders;

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


    public void addReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }

    public ArrayList<Reminder> getReminders(){
        return this.reminders;
    }
}
