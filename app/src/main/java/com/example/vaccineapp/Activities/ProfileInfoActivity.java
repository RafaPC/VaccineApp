package com.example.vaccineapp.Activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.vaccineapp.R;
import com.example.vaccineapp.model.ProfilesManager;

import java.util.ArrayList;

public class ProfileInfoActivity extends AppCompatActivity {
    private TextView textName;
    private ImageView imageProfile;
    private TextView textAge;
    private TextView textKidsNumber;
    private TextView textBirth;
    private ListView listInformation;
    private ImageButton buttonPrev;
    private ImageButton buttonNext;
    private LinearLayout layoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        this.layoutProfile = findViewById(R.id.layoutProfile);
        this.buttonNext = findViewById(R.id.buttonNext);
        this.buttonPrev = findViewById(R.id.buttonPrev);
        this.textName = findViewById(R.id.textName);
        this.imageProfile = findViewById(R.id.imgProfile);
        this.textAge = findViewById(R.id.textAge);
        this.textKidsNumber = findViewById(R.id.textKidsNumber);
        this.textBirth = findViewById(R.id.txtBirth);
        this.listInformation = findViewById(R.id.listInformation);

        findViewById(R.id.imgEdit).setOnClickListener((View v) -> {
                startActivity(new Intent(getApplicationContext(), ProfileEditActivity.class));
        });
    }

    private class InfoAdapter extends BaseAdapter {

        private ArrayList<String> informationList;

        @Override
        public int getCount() {
            return this.informationList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public InfoAdapter(ArrayList<String> informations) {
            this.informationList = informations;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_profile_info, container, false);
            }
            LinearLayout informationInfoView = (LinearLayout) convertView;
            String information = this.informationList.get(position);
            ((TextView) informationInfoView.findViewById(R.id.textInformation)).setText(information);

            return informationInfoView;
        }
    }

    public void updateProfile() {
        this.textName.setText(ProfilesManager.getProfile().getName());
        this.textAge.setText("" + ProfilesManager.getProfile().getAge());
        this.imageProfile.setImageResource(ProfilesManager.getProfile().getImage());
        this.textKidsNumber.setText("" + ProfilesManager.getProfile().getChildren());
        this.textBirth.setText(ProfilesManager.getProfile().getBirthdateString());
        this.listInformation.setAdapter(new ProfileInfoActivity.InfoAdapter(ProfilesManager.getProfile().getInformation()));
        ProfilesManager.updateButtons(this.buttonNext, this.buttonPrev);
    }


    @Override
    public void onResume() {
        super.onResume();
        updateProfile();
    }

    public void previousProfile(View view) {
        ProfilesManager.indexProfile--;
        YoYo.with(Techniques.SlideOutRight)
                .duration(175)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        updateProfile();
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(175)
                                .playOn(findViewById(R.id.layoutProfile));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(this.layoutProfile);
    }

    public void nextProfile(View view) {
        ProfilesManager.indexProfile++;
        YoYo.with(Techniques.SlideOutLeft)
                .duration(175)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        updateProfile();
                        YoYo.with(Techniques.SlideInRight)
                                .duration(175)
                                .playOn(findViewById(R.id.layoutProfile));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(this.layoutProfile);
    }
}
