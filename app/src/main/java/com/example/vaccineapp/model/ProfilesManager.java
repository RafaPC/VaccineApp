package com.example.vaccineapp.model;

import android.widget.ImageButton;
import com.example.vaccineapp.R;
import com.example.vaccineapp.model_kotlin.Appointment;
import com.example.vaccineapp.model_kotlin.Profile;

import java.util.ArrayList;

public class ProfilesManager {
    public static int indexProfile = 0;
    public static ArrayList<Profile> profiles = new ArrayList<>();

    public static Profile getProfile(){
        return ProfilesManager.profiles.get(ProfilesManager.indexProfile);
    }

    public static void updateButtons(ImageButton buttonNext, ImageButton buttonPrev) {
        if (indexProfile == 0) {
            buttonPrev.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            buttonPrev.setEnabled(false);
        } else {
            buttonPrev.setBackgroundResource(R.drawable.gradiant_bar);
            buttonPrev.setEnabled(true);
        }

        if (indexProfile == ProfilesManager.profiles.size() - 1) {
            buttonNext.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            buttonNext.setEnabled(false);
        } else {
            buttonNext.setBackgroundResource(R.drawable.gradiant_bar);
            buttonNext.setEnabled(true);
        }
    }

    public static ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for(int i = 0; i < profiles.size(); i++){
            Profile profile = profiles.get(i);
            for(int j = 0; j < profile.getAppointments().size(); j++){
                appointments.add(profile.getAppointments().get(j));
            }
        }
        return appointments;
    }

    public static ArrayList<Appointment> getAppointmentsFromDate(int year, int month, int day){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for(int i = 0; i < profiles.size(); i++){
            Profile profile = profiles.get(i);
            appointments.addAll(profile.getAppointmentsFromDate(year, month, day));
        }
        return appointments;
    }

}