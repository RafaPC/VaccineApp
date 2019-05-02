package com.example.vaccineapp.model;

import com.example.vaccineapp.model_kotlin.InfoElement;

import java.util.ArrayList;

public class TimelineStage {

    private ArrayList<InfoElement> timelineGroup;

    public TimelineStage(){
        this.timelineGroup = new ArrayList<>();
    }

    public int getSize(){
        return this.timelineGroup.size();
    }

    public void addInfoElement(InfoElement info){
        this.timelineGroup.add(info);
    }

    public InfoElement getInfoElement(int position){
        return this.timelineGroup.get(position);
    }
}