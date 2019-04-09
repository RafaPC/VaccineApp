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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileInfoActivity extends AppCompatActivity {
    private TextView textName;
    private ImageView imageProfile;
    private TextView textAge;
    private TextView textKidsNumber;
    private TextView textBirth;
    private ListView listInformation;

    private Profile profile;
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        this.textName = findViewById(R.id.textAge);
        this.imageProfile = findViewById(R.id.imageProfile);
        this.textAge = findViewById(R.id.textAge);
        this.textKidsNumber = findViewById(R.id.textKidsNumber);
        this.textBirth = findViewById(R.id.textBirth);
        this.listInformation = findViewById(R.id.listInformation);

        this.profile = new Profile("Hatty Hattingtong", Color.CYAN,"09-08-1983");
        this.profile.addInformation("Pregnant");

        this.textName.setText(this.profile.getName());
        this.textAge.setText("33");
        this.textKidsNumber.setText("2");
        this.textBirth.setText(this.profile.getBirthdate());

    }
}
