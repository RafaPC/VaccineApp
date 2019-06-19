package com.example.vaccineapp.Activities;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private LinearLayout layoutProfile;
    private TextView textName;
    private ImageView imageProfile;
    private ListView listReminders;
    private ImageButton buttonNext;
    private ImageButton buttonPrev;
    private static int indexSelectedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.layoutProfile = findViewById(R.id.layoutProfile);
        this.imageProfile = findViewById(R.id.imgProfile);
        this.buttonNext = findViewById(R.id.buttonNext);
        this.buttonPrev = findViewById(R.id.buttonPrev);
        this.textName = findViewById(R.id.textName);
        this.listReminders = findViewById(R.id.listReminders);
    }

    public void changeToTimeline(View view) {
        Intent intent = new Intent(this, ProfileTimeLineActivity.class);
        startActivity(intent);
    }

    public void changeToProfileInfo(View view) {
        Intent intent = new Intent(this, ProfileInfoActivity.class);
        startActivity(intent);
    }

    private class RemindersAdapter extends BaseAdapter {

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

        public RemindersAdapter(ArrayList<InfoElement> reminders) {
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
            reminderInfoView.setTag(position);

            if(ProfilesManager.getProfile().getReminders().get(position).getAlertLevel() != 1){
                reminderInfoView.setOnClickListener((View v) -> {
                    ProfileActivity.indexSelectedInfo = (int) v.getTag();
                    CustomDialogClass cdd = new CustomDialogClass(ProfileActivity.this);
                    cdd.setCanceledOnTouchOutside(false);
                    //cdd.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    cdd.show();
                });

            }

            return reminderInfoView;
        }
    }

    protected void updateProfile() {
        this.textName.setText(ProfilesManager.getProfile().getName());
        this.imageProfile.setImageResource(ProfilesManager.getProfile().getImage());
        this.listReminders.setAdapter(new ProfileActivity.RemindersAdapter(ProfilesManager.getProfile().getReminders()));
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

    private class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity c;
        public Dialog d;
        public Button yes, no;

        public CustomDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_dialog_check_reminder);
            String dialogBody = "Are you sure to check your reminder for \"";
            ((TextView) findViewById(R.id.dialogBody)).setText(dialogBody + ProfilesManager.getProfile().getReminders().get(ProfileActivity.indexSelectedInfo).getInformation() + "\"");
            yes = findViewById(R.id.btnYes);
            no = findViewById(R.id.btnCancel);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnYes:
                    ProfilesManager.getProfile().checkReminder(ProfileActivity.indexSelectedInfo);
                    updateProfile();
                    dismiss();
                    break;
                case R.id.btnCancel:
                    dismiss();
                    break;
                default:
                    break;
            }
        }
    }
}
