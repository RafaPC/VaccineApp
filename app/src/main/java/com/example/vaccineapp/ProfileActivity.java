package com.example.vaccineapp;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    protected static ArrayList<Profile> profiles;
    private LinearLayout layoutProfile;
    private TextView textName;
    private ImageView imageProfile;
    private ListView listInformation;
    private ArrayList<InfoElement> reminders;
    private ListView listReminders;
    private Profile profile;
    protected int indexProfile;
    private ImageButton buttonNext;
    private ImageButton buttonPrev;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.layoutProfile = findViewById(R.id.layoutProfile);
        this.indexProfile = 0;
        this.buttonNext = findViewById(R.id.buttonRight);
        this.buttonPrev = findViewById(R.id.buttonLeft);
        ProfileActivity.profiles = new ArrayList<>();
        ProfileActivity.profiles.add(new Profile("Juan Carlos", Color.RED, "12-07-1995"));
        ProfileActivity.profiles.add(new Profile("Eric Surname", Color.GREEN, "27-03-1994"));
        ProfileActivity.profiles.add(new Profile("Test", Color.BLUE, "27-03-1994"));
        this.profile = ProfileActivity.profiles.get(0);
        this.textName = findViewById(R.id.textName);
        this.reminders = new ArrayList<>();
        this.listReminders = findViewById(R.id.listReminders);
        this.reminders.add(new InfoElement("Vaccination for death", -2));
        this.reminders.add(new InfoElement("Vaccination for aids", -2));
        this.reminders.add(new InfoElement("Vaccination for air allergy", -1));
        this.listReminders.setAdapter(new ProfileActivity.MiAdaptador(this.reminders));
    }

    public void changeToTimeline(View view){
        Intent intent = new Intent(this, TimeLineActivity.class);
        startActivity(intent);
    }

    public void changeToProfileInfo(View view){
        Intent intent = new Intent(this, ProfileInfoActivity.class);
        startActivity(intent);
    }

    private class MiAdaptador extends BaseAdapter {

        private ArrayList<InfoElement> reminders;

        @Override
        public int getCount() {
            return this.reminders.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public MiAdaptador(ArrayList<InfoElement> reminders){
            this.reminders = reminders;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_reminders, container, false);
            }
            LinearLayout reminderInfoView = (LinearLayout) convertView;
            InfoElement reminder = this.reminders.get(position);
            ((TextView) reminderInfoView.findViewById(R.id.textReminder)).setText(reminder.getInformation());
            ((ImageView) reminderInfoView.findViewById(R.id.imgReminder)).setImageResource(reminder.getAlertResource());

            //reminderInfoView.setTag(position);

            /*reminderInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.showToast(getApplicationContext(), "Now it shows the profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
                }
            });*/

            return reminderInfoView;
        }
    }
    protected void updateProfile(){
        this.textName.setText(this.profile.getName());
        if(this.indexProfile == 0){
            this.buttonPrev.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            this.buttonPrev.setEnabled(false);
        }else{
            this.buttonPrev.setBackgroundResource(R.drawable.gradiant_bar);
            this.buttonPrev.setEnabled(true);
        }

        if(this.indexProfile == ProfileActivity.profiles.size()-1){
            this.buttonNext.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            this.buttonNext.setEnabled(false);
        }else{
            this.buttonNext.setBackgroundResource(R.drawable.gradiant_bar);
            this.buttonNext.setEnabled(true);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        updateProfile();
    }

    public void previousProfile(View view){
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
                                .playOn(findViewById(R.id.layoutProfile));
                        updateProfile();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(this.layoutProfile);

        this.profile = ProfileActivity.profiles.get(--this.indexProfile);
    }

    public void nextProfile(View view){
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
                                .playOn(findViewById(R.id.layoutProfile));
                        updateProfile();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(this.layoutProfile);
        this.profile = ProfileActivity.profiles.get(++this.indexProfile);
    }
}
