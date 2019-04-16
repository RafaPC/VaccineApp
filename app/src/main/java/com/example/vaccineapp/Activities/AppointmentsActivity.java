package com.example.vaccineapp.Activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaccineapp.custom.CustomEventDecorator;
import com.example.vaccineapp.model.Appointment;
import com.example.vaccineapp.model.Profile;
import com.example.vaccineapp.R;
import com.example.vaccineapp.model.ProfilesManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    private ListView listAppointments;
    private MaterialCalendarView calendarAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        this.listAppointments = findViewById(R.id.listAppointments);
        this.calendarAppointments = findViewById(R.id.calendarView);
        JodaTimeAndroid.init(this);

        this.calendarAppointments.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                int year = calendarDay.getYear();
                int month = calendarDay.getMonth();
                int day = calendarDay.getDay();
                ((ListView) findViewById(R.id.listAppointments)).setAdapter(new AppointmentsAdapter(year, month, day));
            }
        });

        this.calendarAppointments.setSelectedDate(CalendarDay.today());
        this.calendarAppointments.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay calendarDay) {
                for(int i = 0; i < ProfilesManager.profiles.size(); i++){
                    Profile profile = ProfilesManager.profiles.get(i);
                    for(int j = 0; j < profile.getAppointments().size(); j++){
                        LocalDateTime date = profile.getAppointments().get(j).getDate();
                        if(date.getYear() == calendarDay.getYear() && date.getMonthOfYear() == calendarDay.getMonth() && date.getDayOfMonth() == calendarDay.getDay()){
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public void decorate(DayViewFacade dayViewFacade) {
                dayViewFacade.addSpan(new DotSpan(10, Color.rgb(255,0,0)));
            }
        });

        //this.calendarAppointments.addDecorator(new CustomEventDecorator());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class AppointmentsAdapter extends BaseAdapter {

        private ArrayList<Appointment> appointments;

        @Override
        public int getCount() {
            return this.appointments.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public AppointmentsAdapter(int year, int month, int day) {
            this.appointments = ProfilesManager.getAppointmentsFromDate(year, month, day);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_appointment_info, container, false);
            }
            LinearLayout appointmentInfoView = (LinearLayout) convertView;
            Appointment appointment = this.appointments.get(position);
            ((TextView) appointmentInfoView.findViewById(R.id.textAppointmentReason)).setText(appointment.getReason());
            ((TextView) appointmentInfoView.findViewById(R.id.textAppointmentHour)).setText(appointment.getDateString());
            // ((ImageView) appointmentInfoView.findViewById(R.id.imgAppointmentDoctor)).setImageResource(appointment.getDoctor().getImage());
            /*appointmentInfoView.setTag(position);
                appointmentInfoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfileActivity.indexSelectedInfo = (int) v.getTag();
                        ProfileActivity.CustomDialogClass cdd = new ProfileActivity.CustomDialogClass(ProfileActivity.this);
                        cdd.setCanceledOnTouchOutside(false);
                        //cdd.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        cdd.show();
                    }
                });
            */
            return appointmentInfoView;
        }
    }
}
