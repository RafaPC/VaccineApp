package com.example.vaccineapp.Activities;

import android.animation.Animator;
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
import com.example.vaccineapp.model_kotlin.InfoElement;
import com.example.vaccineapp.R;
import com.example.vaccineapp.model.ProfilesManager;
import com.example.vaccineapp.model.TimelineStage;

import java.util.ArrayList;

public class ProfileTimeLineActivity extends AppCompatActivity {
    private TextView textName;
    private ListView listTimeline;
    private ImageView imageProfile;
    private ImageButton buttonPrev;
    private ImageButton buttonNext;
    private LinearLayout layoutProfile;
    private TimelineAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_timeline);
        this.textName = findViewById(R.id.textName);
        this.listTimeline = findViewById(R.id.listTimeline);
        this.imageProfile = findViewById(R.id.imgProfile);
        this.buttonPrev = findViewById(R.id.buttonPrev);
        this.buttonNext = findViewById(R.id.buttonNext);
        this.layoutProfile = findViewById(R.id.layoutProfile);
    }


    private class TimelineAdapter extends BaseAdapter {

        private ArrayList<TimelineStage> arrayTimeline;

        @Override
        public int getCount() {
            return this.arrayTimeline.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public TimelineAdapter(ArrayList<TimelineStage> arrayTimeline) {
            this.arrayTimeline = arrayTimeline;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_timeline, container, false);
            }
            LinearLayout stageInfoView = (LinearLayout) convertView;
            TimelineStage stage = this.arrayTimeline.get(position);
            ((TextView) stageInfoView.findViewById(R.id.textStage)).setText(stage.getInfoElement(0).getInformation());
            ((ImageView) stageInfoView.findViewById(R.id.imgTitleTimeline)).setImageResource(stage.getInfoElement(0).getAlertResource());
            LinearLayout infoLayout = stageInfoView.findViewById(R.id.layout_textTimeline);
            infoLayout.removeAllViews();
            for (int i = 1; i < stage.getSize(); i++) {
                InfoElement actualElement = stage.getInfoElement(i);
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.timeline_information, infoLayout, false);
                ((TextView) linearLayout.findViewById(R.id.textInformation)).setText(actualElement.getInformation());
                ((ImageView) linearLayout.findViewById(R.id.imgAlert)).setImageResource(actualElement.getAlertResource());
                infoLayout.addView(linearLayout);
            }
            //((TextView) profileInfoView.findViewById(R.id.profile_name)).setText(Profile.getName());
            //((TextView)profileInfoView.findViewById(R.id.profile_age)).setText(Profile.getBirthdate());
            //profileInfoView.setTag(position);

            /*profileInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.showToast(getApplicationContext(), "Now it shows the Profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
                }
            });*/

            return stageInfoView;
        }
    }

    protected void updateProfile() {
        this.textName.setText(ProfilesManager.getProfile().getName());
        this.adapter = new TimelineAdapter(ProfilesManager.getProfile().getTimeline());
        this.imageProfile.setImageResource(ProfilesManager.getProfile().getImage());
        this.listTimeline.setAdapter(this.adapter);
        ProfilesManager.updateButtons(this.buttonNext, this.buttonPrev);
        this.listTimeline.setSelection(this.adapter.getCount() - 1);
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
