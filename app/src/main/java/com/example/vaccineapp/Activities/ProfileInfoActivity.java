package com.example.vaccineapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaccineapp.Classes.Profile;
import com.example.vaccineapp.R;

import java.util.ArrayList;

public class ProfileInfoActivity extends AppCompatActivity {
    private TextView textName;
    private ImageView imageProfile;
    private TextView textAge;
    private TextView textKidsNumber;
    private TextView textBirth;
    private ListView listInformation;

    private Profile profile;
    private ArrayList<String> informations;
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        this.textName = findViewById(R.id.textName);
        this.imageProfile = findViewById(R.id.imageProfile);
        this.textAge = findViewById(R.id.textAge);
        this.textKidsNumber = findViewById(R.id.textKidsNumber);
        this.textBirth = findViewById(R.id.textBirth);
        this.listInformation = findViewById(R.id.listInformation);
        this.informations = new ArrayList<>();
        this.informations.add("Pre-pregnant");
        this.informations.add("Pre-somethingElse");
        this.informations.add("Erik just spilled his sparkling water all over his laptop");
        this.profile = new Profile("Hatty Hattingtong", Color.CYAN,"09-08-1983");
        this.profile.addInformation("Pregnant");

        this.textName.setText(this.profile.getName());
        this.textAge.setText("33");
        this.textKidsNumber.setText("2");
        this.textBirth.setText(this.profile.getBirthdate());

        this.listInformation.setAdapter(new ProfileInfoActivity.MiAdaptador(this.informations));
    }

    private class MiAdaptador extends BaseAdapter {

        private ArrayList<String> informations;

        @Override
        public int getCount() {
            return this.informations.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public MiAdaptador(ArrayList<String> informations){
            this.informations = informations;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_profile_info, container, false);
            }
            LinearLayout informationInfoView = (LinearLayout) convertView;
            String information = this.informations.get(position);
            ((TextView) informationInfoView.findViewById(R.id.textInformation)).setText(information);

            //informationInfoView.setTag(position);

            /*reminderInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.showToast(getApplicationContext(), "Now it shows the profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
                }
            });*/

            return informationInfoView;
        }
    }
}
