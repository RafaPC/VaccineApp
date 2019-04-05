package com.example.vaccineapp;

public class Profile {
    private String name;
    private int color;

    public Profile(String name, int color){
        this.name = name;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
