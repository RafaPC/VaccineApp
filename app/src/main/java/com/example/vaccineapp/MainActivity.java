package com.example.vaccineapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                    //intent = new Intent(getApplicationContext(), MainActivity.class);
                    //startActivity(intent);
                    break;
                case R.id.nav_profile:
                    //Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    };

    public void changeToFindVaccine(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Find Vaccine' screen", Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToHealthStates(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Health States' screen", Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void changeToAppointment(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'AppointmentsActivity' screen", Toast.LENGTH_LONG).show();
        intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    public void changeToScheduler(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Scheduler' screen", Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }


    public void changeToFindDoctors(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Find Doctors' screen", Toast.LENGTH_LONG).show();

    }

    public void changeToFindHospitals(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Hospitals' screen", Toast.LENGTH_LONG).show();

    }

}