package com.example.vaccineapp.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.vaccineapp.Classes.DrawCircle;
import com.example.vaccineapp.Classes.Profile;
import com.example.vaccineapp.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private List<Profile> profiles;
    private int profileIndex = 0;
    private TextView profileName;
    private LinearLayout colorContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        this.profileName = findViewById(R.id.profile_name);
        this.profiles = new ArrayList<>();
        this.profiles.add(new Profile("Me", Color.BLUE, ""));
        this.profiles.add(new Profile("Child 2", Color.RED, ""));
        this.profiles.add(new Profile("Child 3", Color.GREEN, ""));
        this.colorContainer = findViewById(R.id.circlePlace);
        changeProfile(3);

    }

    public void previousProfile(View view) {
        if (this.profileIndex != 0) {
            this.profileIndex--;
            changeProfile(0);
        }
    }

    public void nextProfile(View view) {
        if (this.profileIndex != this.profiles.size() - 1) {
            this.profileIndex++;
            changeProfile(1);
        }
    }

    private void changeProfile(int direction) {
        if(direction == 0){
            YoYo.with(Techniques.SlideOutRight)
                    .duration(300)
                    .playOn(this.profileName);
            YoYo.with(Techniques.SlideOutRight)
                    .duration(300)
                    .playOn(this.colorContainer);
        }else if (direction == 1){
        YoYo.with(Techniques.SlideOutLeft)
                .duration(300)
                .playOn(this.profileName);
            YoYo.with(Techniques.SlideOutLeft)
                    .duration(300)
                    .playOn(this.colorContainer);
        }
        Profile selectedProf = this.profiles.get(this.profileIndex);
        this.profileName.setText(selectedProf.getName());
        this.colorContainer.removeAllViews();
        View circle = new DrawCircle(getApplicationContext(), selectedProf.getColor());
        this.colorContainer.addView(circle);

       if(direction == 0){
           YoYo.with(Techniques.SlideInLeft)
                   .duration(300)
                   .playOn(this.profileName);
           YoYo.with(Techniques.SlideInLeft)
                   .duration(300)
                   .playOn(this.colorContainer);
       }else if (direction == 1){
           YoYo.with(Techniques.SlideInRight)
                   .duration(300)
                   .playOn(this.profileName);
           YoYo.with(Techniques.SlideInRight)
                   .duration(300)
                   .playOn(this.colorContainer);
       }

    }

}
