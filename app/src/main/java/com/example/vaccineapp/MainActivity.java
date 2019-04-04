package com.example.vaccineapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradiant_bar));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();

                    break;
                case R.id.nav_settings:
                    Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_LONG).show();

                    break;
            }
            return false;
        }
    };

    public void changeToFindDoctors(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Find Doctors' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToHospitals(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Hospitals' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToHistorical(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Historical or something' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToAppointment(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Appointments' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToEmergency(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Emergency' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToMedicalShop(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Medical Shop' screen",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

}
