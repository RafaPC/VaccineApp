package com.example.vaccineapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    Intent intent = null;
    String email = "connect@gmail.com";
    String pass = "connect";
    TextInputEditText textEmail, textPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);
        Button btnSignIn = findViewById(R.id.btnsignIn);

        textEmail = findViewById(R.id.textEmail);
        textPass = findViewById(R.id.textpass);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textEmail.getText().toString().equals(email) && textPass.getText().toString().equals(pass)) {
                    //Toast.makeText(getApplicationContext(), "OJ", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));


                } else  {
                    if (textEmail.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Empty Email", Toast.LENGTH_LONG).show();
                    }
                    else if (textPass.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Empty Password", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(!textEmail.getText().toString().equals(email)){
                            Toast.makeText(getApplicationContext(), "Wrong email", Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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

    public void createAccount(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'create Account' screen", Toast.LENGTH_LONG).show();

    }

    public void forgetPass(View v) {
        Toast.makeText(getApplicationContext(), "Change to 'Forget Password' screen", Toast.LENGTH_LONG).show();

    }

}
