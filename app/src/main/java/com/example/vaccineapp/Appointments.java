package com.example.vaccineapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Appointments extends AppCompatActivity {

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
        this.profiles.add(new Profile("Me", Color.BLUE));
        this.profiles.add(new Profile("Child 2", Color.RED));
        this.profiles.add(new Profile("Child 3", Color.GREEN));
        this.colorContainer = findViewById(R.id.circlePlace);
        changeProfile();

    }

public void previousProfile(View view){
    if(this.profileIndex != 0){
        this.profileIndex--;
        changeProfile();
    }
}

public void nextProfile(View view){
    if(this.profileIndex != this.profiles.size() - 1){
        this.profileIndex++;
        changeProfile();
    }
}

private void changeProfile(){
        Profile selectedProf = this.profiles.get(this.profileIndex);
        this.profileName.setText(selectedProf.getName());
        this.colorContainer.removeAllViews();
        this.colorContainer.addView(new DrawCircle(getApplicationContext(), selectedProf.getColor()));
}

}
