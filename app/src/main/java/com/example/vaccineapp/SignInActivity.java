package com.example.vaccineapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout layout_inputEmail;
    private TextInputEditText inputEmail;
    private TextInputLayout layout_inputPassword;
    private TextInputEditText inputPassword;
    private Button signInButton;

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        this.layout_inputEmail = findViewById(R.id.layout_input_email);
        this.inputEmail = findViewById(R.id.inputEmail);
        this.layout_inputPassword = findViewById(R.id.layout_input_password);
        this.inputPassword = findViewById(R.id.inputPassword);
        this.signInButton = findViewById(R.id.btn_signIn);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);

        this.inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputs(new View(getApplicationContext()));
            }
        });

        this.inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputs(new View(getApplicationContext()));
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
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void forgotPass(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void checkInputs(View view) {
        boolean error = false;
        if (this.inputEmail.getText().length() > 0 && this.inputPassword.getText().length() > 0) {
            this.signInButton.setBackgroundResource(R.drawable.gradiant_bar);
            this.signInButton.setEnabled(true);
        } else {
            this.signInButton.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            this.signInButton.setEnabled(false);
        }
    }

    public void login(View view) {
        this.inputEmail.clearFocus();
        this.inputPassword.clearFocus();
        String email = "" + ((TextView)this.inputEmail).getText();
        if (Functions.validateEmail(email)) {
            Functions.showToast(getApplicationContext(), "Valid email");
        }else{
            Functions.showToast(getApplicationContext(), "Invalid email");
        }
    }

}
