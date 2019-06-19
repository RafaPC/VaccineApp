package com.example.vaccineapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccineapp.model.Functions;
import com.example.vaccineapp.R;

public class SignInActivity extends AppCompatActivity {
    private TextInputEditText inputEmail;
    private TextInputEditText inputPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPass);
        btnSignIn = findViewById(R.id.btnSignIn);
        ((BottomNavigationView)findViewById(R.id.bottom_navigation)).setOnNavigationItemSelectedListener(itemSelect);


        //Sets TextWatchers to both inputs in order to enable or disable the button when the text is changed
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputs();
            }
        });
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                checkInputs();
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect = (@NonNull MenuItem menuItem) -> {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    };

    public void createAccount(View v) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void forgotPass(View v) {
        startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
    }

    private void checkInputs() {
        if (inputEmail.getText().length() > 0 && inputPassword.getText().length() > 0) {
            btnSignIn.setBackgroundResource(R.drawable.button_gradient);
            btnSignIn.setEnabled(true);
        } else {
            btnSignIn.setBackgroundResource(R.drawable.button_gradient_disabled);
            btnSignIn.setEnabled(false);
        }
    }

    public void login(View v) {
        inputEmail.clearFocus();
        inputPassword.clearFocus();

        //TextInputEditText.getText() returns CharSequence, so if added to "" it's smart casted to String
        String email = "" + inputEmail.getText();

        if (Functions.validateEmail(email)) {
            Toast.makeText(getApplicationContext(), "Valid email", Toast.LENGTH_LONG).show();
            //TODO: this should login the user and change to the main menu or the profiles screen
            startActivity(new Intent(getApplicationContext(), ProfilesListActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
        }
    }

}
