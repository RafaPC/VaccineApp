package com.example.vaccineapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vaccineapp.model.Functions;
import com.example.vaccineapp.R;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText inputName;
    private TextInputEditText inputEmail;
    private TextInputEditText inputTelephone;
    private TextInputEditText inputPassword1;
    private TextInputEditText inputPassword2;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputTelephone = findViewById(R.id.inputTelephone);
        inputPassword1 = findViewById(R.id.inputPassword1);
        inputPassword2 = findViewById(R.id.inputPassword2);
        btnSignUp = findViewById(R.id.btnSignUp);

        this.inputName.addTextChangedListener(new TextWatcher() {
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

        this.inputEmail.addTextChangedListener(new TextWatcher() {
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

        this.inputPassword1.addTextChangedListener(new TextWatcher() {
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

        this.inputPassword2.addTextChangedListener(new TextWatcher() {
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

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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
        }
    };

    private void checkInputs(){
        //TextInputEditText.getText() returns CharSequence, so if added to "" it's smart casted to String
        String name = "" + inputName.getText();
        String email = "" + inputEmail.getText();
        String password1 = "" + inputPassword1.getText();
        String password2 = "" + inputPassword2.getText();
        if(name.length() != 0 && email.length() != 0 && password1.length() != 0 && password2.length() != 0){
            btnSignUp.setBackgroundResource(R.drawable.button_gradient);
            btnSignUp.setEnabled(true);
        }else{
            btnSignUp.setBackgroundResource(R.drawable.button_gradient_disabled);
            btnSignUp.setEnabled(false);
        }


    }
    public void signUp(View view) {
        String name = "" + inputName.getText();
        String email = "" + inputEmail.getText();
        String password1 = "" + inputPassword1.getText();
        String password2 = "" + inputPassword2.getText();
        if(name.length() == 0){
            Toast.makeText(getApplicationContext(), "You must write a name", Toast.LENGTH_LONG).show();
        } else if (!Functions.validateEmail(email)) {
            //TODO: the system should check whether the email is already used in another profile
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
        } else if (password1.length() == 0) {
            Toast.makeText(getApplicationContext(), "You must write a password", Toast.LENGTH_LONG).show();
        } else if (password2.length() == 0) {
            Toast.makeText(getApplicationContext(), "You must confirm the password", Toast.LENGTH_LONG).show();
        } else if (!password1.equals(password2)) {
            Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_LONG).show();
        } else {
            //TODO: the system should also check whether the number has a good format or not
            Toast.makeText(getApplicationContext(), "Everything is fine", Toast.LENGTH_LONG).show();
        }
    }


}
