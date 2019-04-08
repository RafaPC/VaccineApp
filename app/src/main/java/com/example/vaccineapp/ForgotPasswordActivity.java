package com.example.vaccineapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextInputEditText inputEmail;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        this.inputEmail = findViewById(R.id.inputEmail);
        this.buttonReset = findViewById(R.id.btn_resetPassword);

        this.inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //Maybe put that when the user clicks the check button on the keyboard, resetPassword() is called
    private void checkInput(){
        if(this.inputEmail.getText().length() > 0){
            this.buttonReset.setBackgroundResource(R.drawable.gradiant_bar);
            this.buttonReset.setEnabled(true);
        }else{
            this.buttonReset.setBackgroundResource(R.drawable.gradiant_bar_disabled);
            this.buttonReset.setEnabled(false);
        }
    }

    public void resetPassword(View view){
        String email = "" + ((TextView)this.inputEmail).getText();
        if(Functions.validateEmail(email)){
            Functions.showToast(getApplicationContext(), "Now checks if the email exists and then send the message");
        }else{
            Functions.showToast(getApplicationContext(), "Invalid email");
        }
    }
}
