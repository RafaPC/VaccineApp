package com.example.vaccineapp.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.model.Functions;
import com.example.vaccineapp.model.ProfilesManager;
import com.example.vaccineapp.model.Service;

import java.util.ArrayList;

public class ProfileEditActivity extends AppCompatActivity {

    private ArrayList<String> availableInfo;
    private int[] selectedInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        availableInfo = new ArrayList<>();

        //Datepicker dialog to change birth date
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ProfilesManager.getProfile().setBirthdate(getApplicationContext(), year, month, dayOfMonth);
                Functions.showToast(getApplicationContext(), "Año " + year + " Mes " + month + " Día " + dayOfMonth);
            }
        }, 2000, 5, 5);

        findViewById(R.id.txtBirth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }


    private class InformationAdapter extends BaseAdapter {

        private ArrayList<String> information;

        @Override
        public int getCount() {
            return this.information.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public InformationAdapter(ArrayList<String> information) {
            this.information = information;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_spinner_service, container, false);
            }
            LinearLayout serviceInfoView = (LinearLayout) convertView;
            String information = this.information.get(position);
            ((TextView) serviceInfoView.findViewById(R.id.txtServiceName)).setText(information);
            ImageView imgCheck = serviceInfoView.findViewById(R.id.imgServiceCheck);

            if (selectedInfo[position] == 1) {
                ((ImageView) serviceInfoView.findViewById(R.id.imgServiceCheck)).setImageResource(R.drawable.check_filled);
                imgCheck.setTag(1);
            } else {
                imgCheck.setTag(0);
            }
            //((ImageView) serviceInfoView.findViewById(R.id.imgAppointmentPhoto)).setImageResource(appointment.getProfile().getPhoto());

            serviceInfoView.setTag(position);
            serviceInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((int) imgCheck.getTag() == 0) {
                        imgCheck.setImageResource(R.drawable.check_filled);
                        imgCheck.setTag(1);
                        selectedInfo[position] = 1;
                    } else {
                        imgCheck.setImageResource(R.drawable.check_empty);
                        imgCheck.setTag(0);
                        selectedInfo[position] = 0;
                    }
                }
            });

            serviceInfoView.startAnimation(AnimationUtils.loadAnimation(ProfileEditActivity.this, R.anim.animation_list_scroll));
            return serviceInfoView;
        }
    }
}
