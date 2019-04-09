package com.example.vaccineapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private TextView textName;
    private ImageView imageProfile;
    private TextView textAge;
    private TextView textKidsNumber;
    private TextView textBirth;
    private ListView listInformation;
    private ArrayList<Reminder> reminders;
    private ListView listReminders;

    private Profile profile;
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.reminders = new ArrayList<>();
        this.listReminders = findViewById(R.id.listReminders);
        this.reminders.add(new Reminder("Vaccination for death", 1));
        this.reminders.add(new Reminder("Vaccination for aids", 1));
        this.reminders.add(new Reminder("Vaccination for air allergy", 2));
        this.listReminders.setAdapter(new ProfileActivity.MiAdaptador(this.reminders));
    }

    public void changeToTimeline(View view){
        //Intent intent = new Intent(this, ProfileActivity.class);
        //startActivity(intent);
    }

    public void changeToProfileInfo(View view){
        Intent intent = new Intent(this, ProfileInfoActivity.class);
        startActivity(intent);
    }

    private class MiAdaptador extends BaseAdapter {

        private ArrayList<Reminder> reminders;

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

        public MiAdaptador(ArrayList<Reminder> reminders){
            this.reminders = reminders;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_reminders, container, false);
            }
            LinearLayout reminderInfoView = (LinearLayout) convertView;
            Reminder reminder = this.reminders.get(position);
            ((TextView) reminderInfoView.findViewById(R.id.textReminder)).setText(reminder.getInformation());
            ((ImageView) reminderInfoView.findViewById(R.id.imgReminder)).setImageResource(reminder.getAlertResource());
            reminderInfoView.setTag(position);

            /*reminderInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.showToast(getApplicationContext(), "Now it shows the profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
                }
            });*/

            return reminderInfoView;
        }
    }
}
