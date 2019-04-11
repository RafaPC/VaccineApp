package com.example.vaccineapp.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaccineapp.Classes.Functions;
import com.example.vaccineapp.Classes.Profile;
import com.example.vaccineapp.R;

import java.util.ArrayList;

public class ProfilesListActivity extends AppCompatActivity {

    private ListView profilesList;
    private static ArrayList<Profile> profiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_list);
        this.profilesList = findViewById(R.id.listview_profiles);
        this.profiles.add(new Profile("Mum", Color.CYAN, "1978/07/12"));
        this.profiles.add(new Profile("Child 1", Color.CYAN, "2008/03/23"));
        this.profiles.add(new Profile("Child 2", Color.CYAN, "2013/01/17"));

        this.profilesList.setAdapter(new MiAdaptador(this.profiles));
    }

    private class MiAdaptador extends BaseAdapter {

        private ArrayList<Profile> profiles;

        @Override
        public int getCount() {
            return this.profiles.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public MiAdaptador(ArrayList<Profile> profiles){
            this.profiles = profiles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_profiles, container, false);
            }
            LinearLayout profileInfoView = (LinearLayout) convertView;
            Profile profile = this.profiles.get(position);
            ((TextView) profileInfoView.findViewById(R.id.profile_name)).setText(profile.getName());
            ((TextView)profileInfoView.findViewById(R.id.profile_age)).setText(profile.getBirthdate());
            ((TextView)profileInfoView.findViewById(R.id.profile_info)).setText("Information");
            profileInfoView.setTag(position);

        profileInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.showToast(getApplicationContext(), "Now it shows the profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
            }
        });

            return profileInfoView;
        }
    }
}
