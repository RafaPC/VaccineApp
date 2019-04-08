package com.example.vaccineapp;

public class Profile {
    private String name;
    private int color;
    private String birthdate;

    public Profile(String name, int color, String birthdate){
        this.name = name;
        this.color = color;
        this.birthdate = birthdate;
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
}
