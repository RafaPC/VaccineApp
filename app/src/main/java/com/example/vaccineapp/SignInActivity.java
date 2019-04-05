package com.example.vaccineapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    }
    public void forgetPass(View v){
        Toast.makeText(getApplicationContext(),"Change to 'Forget Password' screen",Toast.LENGTH_LONG).show();
        /*Intent intent = new Intent(this, Appointments.class);
        startActivity(intent);*/
    }

}
