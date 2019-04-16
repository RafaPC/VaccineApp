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
    private Button buttonSignUp;

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
        this.buttonSignUp = findViewById(R.id.btnSignUp);

        this.inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.inputPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.inputPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        String name = "" + this.inputName.getText();
        String email = "" + this.inputEmail.getText();
        String password1 = "" + this.inputPassword1.getText();
        String password2 = "" + this.inputPassword2.getText();
        if(name.length() != 0 && email.length() != 0 && password1.length() != 0 && password2.length() != 0){
            this.buttonSignUp.setBackgroundResource(R.drawable.button_gradient);
            this.buttonSignUp.setEnabled(true);
        }else{
            this.buttonSignUp.setBackgroundResource(R.drawable.button_gradient_disabled);
            this.buttonSignUp.setEnabled(false);
        }


    }
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
