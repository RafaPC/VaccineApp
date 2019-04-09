package com.example.vaccineapp;

public class Reminder {

    private String information;
    private int alert;

    public Reminder(String information, int alert) {
        this.information = information;
        this.alert = alert;
    }

    public String getInformation() {
        return information;
    }

    public int getAlert() {
        return alert;
    }

    public int getAlertResource(){
        if(this.alert == 1){
            return R.drawable.warning_red;
        }else if(this.alert == 2){
            return R.drawable.warning_yellow;
        }else{
            //Here should be another if for returning some kind of info icon, so there would be yellow warning, red warning and info
            return R.drawable.warning_yellow;
        }
    }
}
