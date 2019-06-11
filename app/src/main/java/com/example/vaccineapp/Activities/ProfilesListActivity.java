package com.example.vaccineapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.model.ProfilesManager;
import com.example.vaccineapp.model_kotlin.Profile;

import java.util.ArrayList;

public class ProfilesListActivity extends AppCompatActivity {

    private ListView profilesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_list);
        profilesList = findViewById(R.id.listview_profiles);
    }

    private class ListProfileAdapter extends BaseAdapter {

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

        public ListProfileAdapter(ArrayList<Profile> profiles) {
            this.profiles = profiles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_profiles, container, false);
            }
            LinearLayout profileInfoView = (LinearLayout) convertView;
            Profile profile = profiles.get(position);
            ((ImageView) profileInfoView.findViewById(R.id.imgProfile)).setImageResource(profile.getImage());
            ((TextView) profileInfoView.findViewById(R.id.textProfileName)).setText(profile.getName());
            ((TextView) profileInfoView.findViewById(R.id.textProfileAge)).setText(profile.getAge() + " years - " + profile.getBirthdateString());
            ((TextView) profileInfoView.findViewById(R.id.textProfileInfo)).setText("Information");
            profileInfoView.setTag(position);

            profileInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfilesManager.indexProfile = (int) view.getTag();
                    startActivity(new Intent(ProfilesListActivity.this, ProfileActivity.class));
                }
            });

            return profileInfoView;
        }
    }

    protected void updateProfile() {
        profilesList.setAdapter(new ListProfileAdapter(ProfilesManager.profiles));
    }

    public void createProfile(View view) {
        //TODO: should appear a dialog that lets create a profile
        Toast.makeText(getApplicationContext(), "Now the profile creation screen is shown", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateProfile();
    }
}
