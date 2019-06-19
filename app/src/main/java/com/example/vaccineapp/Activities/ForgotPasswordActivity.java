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

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextInputEditText inputEmail;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        inputEmail = findViewById(R.id.inputEmail);
        btnReset = findViewById(R.id.btn_resetPassword);

        //Sets a TextWatcher (text input listener) to enable the button only when something it's written
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInput();
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

    //Maybe put that when the user clicks the check button on the keyboard, resetPassword() is called
    private void checkInput(){
        if(inputEmail.getText().length() > 0){
            btnReset.setBackgroundResource(R.drawable.button_gradient);
            btnReset.setEnabled(true);
        }else{
            btnReset.setBackgroundResource(R.drawable.button_gradient_disabled);
            btnReset.setEnabled(false);
        }
    }

    public void resetPassword(View v){
        String email = "" + inputEmail.getText();
        if(Functions.validateEmail(email)){
            //TODO: Now checks if the email exists and then send the message
            Toast.makeText(getApplicationContext(), "Recovery email sent", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
        }
    }
}
