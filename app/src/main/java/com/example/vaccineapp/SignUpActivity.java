package com.example.vaccineapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(itemSelect);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelect= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    };
}
