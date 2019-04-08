package com.example.vaccineapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private TextView inputName;
    private TextView inputEmail;
    private TextView inputTelephone;
    private TextView inputPassword1;
    private TextView inputPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);

        this.inputName = findViewById(R.id.inputName);
        this.inputEmail = findViewById(R.id.inputEmail);
        this.inputTelephone = findViewById(R.id.inputTelephone);
        this.inputPassword1 = findViewById(R.id.inputPassword1);
        this.inputPassword2 = findViewById(R.id.inputPassword2);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    };

    public void signUp(View view) {
        boolean error = true;
        String name = "" + this.inputName.getText();
        String email = "" + this.inputEmail.getText();
        String password1 = "" + this.inputPassword1.getText();
        String password2 = "" + this.inputPassword2.getText();
        if(name.length() == 0){
            Functions.showToast(getApplicationContext(), "You must write a name");
        } else if (!Functions.validateEmail(email)) {
            Functions.showToast(getApplicationContext(), "Invalid email");
        } else if (password1.length() == 0) {
            Functions.showToast(getApplicationContext(), "You must write the password");
        } else if (password2.length() == 0) {
            Functions.showToast(getApplicationContext(), "You must confirm the password");
        } else if (!password1.equals(password2)) {
            Functions.showToast(getApplicationContext(), "Passwords must match");
        } else {
            error = false;
            Functions.showToast(getApplicationContext(), "Everything's fine");
        }
    }
}
