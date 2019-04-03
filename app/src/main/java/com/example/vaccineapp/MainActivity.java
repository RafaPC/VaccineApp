package com.example.vaccineapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnFindDocto, btnHospital, btnDoctriod, btnAppointment, btnEmergency, btnMedicalShop;
    ImageButton btnFindDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradiant_bar));
        btnFindDocto = findViewById(R.id.btnFindDocto);
        btnHospital = findViewById(R.id.btnHospital);
        btnDoctriod = findViewById(R.id.btn3);
        btnAppointment = findViewById(R.id.btn4);
        btnEmergency = findViewById(R.id.btn5);
        btnMedicalShop = findViewById(R.id.btn6);
        btnFindDoctor = findViewById(R.id.btnFindDoctor);
        btnFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Find Doctor",Toast.LENGTH_LONG).show();

            }
        });

        btnFindDocto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Find Docto",Toast.LENGTH_LONG).show();
            }
        });
        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Find Hospital",Toast.LENGTH_LONG).show();
            }
        });
        btnDoctriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Doctriod",Toast.LENGTH_LONG).show();
            }
        });
        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Appointment",Toast.LENGTH_LONG).show();
            }
        });
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Emergency",Toast.LENGTH_LONG).show();
            }
        });
        btnMedicalShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is Medical Shop",Toast.LENGTH_LONG).show();
            }
        });
    }

}
