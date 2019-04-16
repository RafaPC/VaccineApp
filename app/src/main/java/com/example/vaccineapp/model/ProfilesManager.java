package com.example.vaccineapp.model;

import android.view.View;
import android.widget.ImageButton;

import com.example.vaccineapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfilesManager {
    public static int indexProfile = 0;
    public static ArrayList<Profile> profiles = new ArrayList<>();

    public static Profile getProfile(){
        return ProfilesManager.profiles.get(ProfilesManager.indexProfile);
    }

    public static void previousProfile(final View layout) {
        /*
        YoYo.with(Techniques.SlideOutRight)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(200)
                                .playOn(layout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(layout);
        */
    }

    public static void nextProfile(final View layout) {
        /*
        YoYo.with(Techniques.SlideOutLeft)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        YoYo.with(Techniques.SlideInRight)
                                .duration(200)
                                .playOn(layout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(layout);
        */
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


    public ArrayList<Appointment> getAllAppointments(){
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

    public static ArrayList<Integer> getProfilesColors(int year, int month, int day){
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 0; i < profiles.size(); i++){
            Profile profile = profiles.get(i);
            for(int j = 0; j < profile.getAppointments().size(); j++){
                Appointment appointment = profile.getAppointments().get(j);
                if(appointment.equals(year, month, day)){
                    colors.add(profile.getColor());
                    continue;
                }
            }
        }
        return colors;
    }
}
