package com.example.vaccineapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.model.Appointment;
import com.example.vaccineapp.model.Service;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class AppointmentDetails extends AppCompatActivity {

    private String reason, hospital, doctor;
    private LocalDateTime dateTime;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        Appointment appointment = AppointmentsActivity.appointment;
//        this.date = dateTime.getDayOfMonth() + " - " + dateTime.toString("MMMM") + " - " + dateTime.getYear();
        ((TextView)findViewById(R.id.txtReason)).setText(appointment.getReason());
        ((TextView)findViewById(R.id.txtDoctor)).setText(appointment.getDoctor());
        ((TextView)findViewById(R.id.txtHospital)).setText(appointment.getHospital());
        ((TextView)findViewById(R.id.txtDate)).setText(appointment.getDateString());
        ((TextView)findViewById(R.id.txtTime)).setText(appointment.getTimeString());
        ((TextView)findViewById(R.id.txtTotalCost)).setText(appointment.getServicesCost() + "€");

        ListView listServices = findViewById(R.id.listServices);
        listServices.setAdapter(new ServicesAdapter(AppointmentsActivity.appointment.getServices()));

    }

    private class ServicesAdapter extends BaseAdapter {

        private ArrayList<Service> services;

        @Override
        public int getCount() {
            return this.services.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public ServicesAdapter(ArrayList<Service> services) {
            this.services = services;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_service, container, false);
            }
            LinearLayout serviceInfoView = (LinearLayout) convertView;
            Service service = this.services.get(position);
            ((TextView) serviceInfoView.findViewById(R.id.txtService)).setText(service.getServiceName());
            ((TextView) serviceInfoView.findViewById(R.id.txtServicePrice)).setText("" + service.getPrice() + "€");


            //appointmentInfoView.startAnimation(AnimationUtils.loadAnimation(AppointmentsActivity.this, R.anim.animation_list_scroll));

            return serviceInfoView;
        }
    }
}
