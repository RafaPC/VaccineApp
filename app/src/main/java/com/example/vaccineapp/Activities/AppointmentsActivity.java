package com.example.vaccineapp.Activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.vaccineapp.model.Appointment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.model.Functions;
import com.example.vaccineapp.model.Profile;
import com.example.vaccineapp.model.ProfilesManager;
import com.example.vaccineapp.model.Service;


import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private ListView listAppointments;
    private static CalendarView calendarView;
    private ArrayList<String> hospitals = new ArrayList<>();
    private String reason, hospital, doctor;
    private long date, time;
    private int selectedHospital, selectedProfile;
    protected static Appointment appointment;
    protected int[] selectedServices;
    private ArrayList<Service> availablesServices;
    private int totalCost;
    private Calendar selectedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        this.availablesServices = new ArrayList<>();
        this.hospitals.add("12 de octubre");
        this.hospitals.add("Hospital 1");
        this.hospitals.add("Hospital 2");
        this.hospitals.add("Tic Hospital");
        this.hospitals.add("...");
        this.hospitals.add("...");

        this.listAppointments = findViewById(R.id.listAppointments);
        this.calendarView = findViewById(R.id.calendarView);
        JodaTimeAndroid.init(this);
        this.calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar calendar = eventDay.getCalendar();
                selectedCalendar = calendar;
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONDAY);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.d("Appointments", "Año: " + year + "- Mes: " + month + "- Dia: " + day);
                ((ListView) findViewById(R.id.listAppointments)).setAdapter(new AppointmentsAdapter(year, month + 1, day));
            }
        });
        Calendar cal = Calendar.getInstance();
        selectedCalendar = cal;
        this.listAppointments.setAdapter(new AppointmentsAdapter(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
        this.availablesServices.add(new Service("Weekly Checkup", 50));
        this.availablesServices.add(new Service("Vaccine for death", 200));
        this.availablesServices.add(new Service("Vaccine for aids", 0));
        this.availablesServices.add(new Service("Vaccine for air allergy", 30));
        this.selectedServices = new int[availablesServices.size()];

        /*OnSelectDateListener listener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<Calendar> calendars) {

            }
        };*/
        loadAppointments();
    }

    public void loadAppointments() {
        List<EventDay> events = new ArrayList<>();
        ArrayList<Appointment> appointments = ProfilesManager.getAllAppointments();
        for (int i = 0; i < appointments.size(); i++) {
            LocalDateTime date = appointments.get(i).getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date.toDate());
            events.add(new EventDay(calendar, R.drawable.appointment_icon));
        }

        this.calendarView.setEvents(events);
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
            ((TextView) appointmentInfoView.findViewById(R.id.textAppointmentHour)).setText(appointment.getTimeString());
            ((ImageView) appointmentInfoView.findViewById(R.id.imgAppointmentPhoto)).setImageResource(appointment.getProfile().getImage());
            appointmentInfoView.setTag(position);
            appointmentInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int indexAppointment = (int) v.getTag();
                    int year = selectedCalendar.get(Calendar.YEAR);
                    int month = selectedCalendar.get(Calendar.MONDAY);
                    int day = selectedCalendar.get(Calendar.DAY_OF_MONTH);
                    AppointmentsActivity.appointment = ProfilesManager.getAppointmentsFromDate(year, month + 1, day).get(indexAppointment);

                    Intent intent = new Intent(getApplicationContext(), AppointmentDetails.class);
                    startActivity(intent);
                }
            });

            appointmentInfoView.startAnimation(AnimationUtils.loadAnimation(AppointmentsActivity.this, R.anim.animation_list_scroll));
            return appointmentInfoView;
        }
    }

    public void createAppointment(View view) {
        reason = "";
        doctor = "";
        selectedHospital = 0;
        selectedProfile = 0;
        date = Calendar.getInstance().getTimeInMillis();
        time = 12 * 3600 * 1000;
        totalCost = 0;
        showCreateAppointment1();
        //Initialize to 0
        for (int i = 0; i < selectedServices.length; i++) {
            selectedServices[i] = 0;
        }
    }

    private void showCreateAppointment1() {
        Dialog dialog_createAppointment = new Dialog(this);
        dialog_createAppointment.setCanceledOnTouchOutside(true);
        dialog_createAppointment.setContentView(R.layout.dialog_create_appointment_1);
        TextView inputReason = dialog_createAppointment.findViewById(R.id.dialogInputReason);
        TextView inputDoctor = dialog_createAppointment.findViewById(R.id.dialogInputDoctor);
        Spinner inputHospital = dialog_createAppointment.findViewById(R.id.dialogSpinnerHospital);
        Spinner inputProfile = dialog_createAppointment.findViewById(R.id.dialogSpinnerProfile);

        inputReason.setText(reason);
        inputDoctor.setText(doctor);

        inputHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHospital = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        inputProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProfile = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dialog_createAppointment.findViewById(R.id.dialogBtnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dialogReason = "" + inputReason.getText();
                String dialogHospital = ((Spinner) dialog_createAppointment.findViewById(R.id.dialogSpinnerHospital)).getSelectedItem().toString();
                String dialogDoctor = "" + inputDoctor.getText();
                if (dialogReason.length() > 0 && dialogHospital.length() > 0 && dialogDoctor.length() > 0) {
                    reason = dialogReason;
                    hospital = dialogHospital;
                    doctor = dialogDoctor;

                    dialog_createAppointment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            showCreateAppointment1_2();
                        }
                    });

                    dialog_createAppointment.dismiss();
                } else {
                    Functions.showToast(getApplicationContext(), "Fill the fields");
                }
            }
        });

        //Load the hospital spinner
        ArrayAdapter<String> hospitalArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        this.hospitals); //selected item will look like a spinner set from XML
        hospitalArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputHospital.setAdapter(hospitalArrayAdapter);
        inputHospital.setSelection(selectedHospital);

        ArrayList<String> profilesNames = new ArrayList<>();
        for (Profile profile : ProfilesManager.profiles) {
            profilesNames.add(profile.getName());
        }
        //Load the spinner
        ArrayAdapter<String> profilesArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        profilesNames); //selected item will look like a spinner set from XML
        profilesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputProfile.setAdapter(profilesArrayAdapter);
        inputProfile.setSelection(selectedProfile);

        dialog_createAppointment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_createAppointment.show();
    }

    private void showCreateAppointment1_2() {
        Dialog dialog_createAppointment = new Dialog(this);
        dialog_createAppointment.setCanceledOnTouchOutside(true);
        dialog_createAppointment.setContentView(R.layout.dialog_create_appointment_1_2);
        TextView textTotalCost = dialog_createAppointment.findViewById(R.id.txtTotalCost);
        textTotalCost.setText(totalCost + " €");
        ListView listServices = dialog_createAppointment.findViewById(R.id.listServices);

        listServices.setAdapter(new ServicesAdapter(this.availablesServices, dialog_createAppointment.findViewById(R.id.txtTotalCost)));

        //Next button onClick listener
        dialog_createAppointment.findViewById(R.id.dialogBtnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_createAppointment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        showCreateAppointment2();
                    }
                });

                dialog_createAppointment.dismiss();
            }
        });

        dialog_createAppointment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_createAppointment.show();
    }

    private void showCreateAppointment2() {
        Dialog dialog_createAppointment = new Dialog(this);
        dialog_createAppointment.setCanceledOnTouchOutside(true);
        dialog_createAppointment.setContentView(R.layout.dialog_create_appointment_2);
        CalendarView calendarView = dialog_createAppointment.findViewById(R.id.calendarView);
        try {
            calendarView.setDate(new Date(date));
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        //Calendar click listener
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                date = eventDay.getCalendar().getTimeInMillis();
            }
        });
        //Next button listener
        dialog_createAppointment.findViewById(R.id.dialogBtnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dismiss listener
                dialog_createAppointment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        showCreateAppointment3();
                    }
                });

                dialog_createAppointment.dismiss();
            }
        });
        dialog_createAppointment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_createAppointment.show();
    }

    private void showCreateAppointment3() {
        Dialog dialog_createAppointment = new TimePickerDialog(AppointmentsActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
                time = (hourOfDay + 1) * 3600 * 1000 + minuteOfHour * 60 * 1000;
                showCreateAppointment4();
            }
        }, 12, 0, false);
        //dialog_createAppointment.setCanceledOnTouchOutside(true);
        //dialog_createAppointment.setContentView(R.layout.dialog_create_appointment_3);
        //dialog_createAppointment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_createAppointment.show();
    }

    private void showCreateAppointment4() {
        long unixTime = this.date + this.time;
        LocalDateTime date = new LocalDateTime(unixTime);
        Dialog dialog_createAppointment = new Dialog(this);
        dialog_createAppointment.setCanceledOnTouchOutside(true);
        dialog_createAppointment.setContentView(R.layout.dialog_create_appointment_4);
        dialog_createAppointment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((TextView) dialog_createAppointment.findViewById(R.id.dialogTxtDoctor)).setText(doctor);
        ((TextView) dialog_createAppointment.findViewById(R.id.dialogTxtHospital)).setText(hospital);
        ((TextView) dialog_createAppointment.findViewById(R.id.dialogTxtDate)).setText(date.toString("dd - MMMM - YYYY"));
        ((TextView) dialog_createAppointment.findViewById(R.id.dialogTxtTime)).setText(date.toString("hh:mm aa"));
        dialog_createAppointment.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CONFIRM BUTTON CLICKED - THE APPOINTMENT IS CREATED
                ArrayList<Service> services = new ArrayList<>();
                /*
                for(int index: selectedServices){
                    services.add(availablesServices.get(index));
                    if(index == 1){

                    }
                }*/

                for(int i = 0; i < availablesServices.size(); i++){
                    if(selectedServices[i] == 1){
                        services.add(availablesServices.get(i));
                    }
                }

                Appointment newAppointment = new Appointment(reason, hospital, doctor, date, services);
                ProfilesManager.profiles.get(selectedProfile).addAppointment(newAppointment);
                loadAppointments();
                dialog_createAppointment.dismiss();
                Functions.showToast(getApplicationContext(), "Appointment created");
                Calendar cal = Calendar.getInstance();
                AppointmentsAdapter adapter = new AppointmentsAdapter(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
                listAppointments.setAdapter(adapter);
                listAppointments.setSelection(adapter.getCount() - 1);
            }
        });
        dialog_createAppointment.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_createAppointment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        showCreateAppointment1();
                    }
                });
                dialog_createAppointment.dismiss();
            }
        });
        dialog_createAppointment.show();
    }

    //Unused
    private void goToAppointmentDetails(int position) {
        long unixTime = 0;
        unixTime = date + time;
        LocalDateTime z = new LocalDateTime(unixTime);
        Intent intent = new Intent(this, AppointmentDetails.class);
        intent.putExtra("appointmentReason", reason);
        intent.putExtra("appointmentHospital", hospital);
        intent.putExtra("appointmentDoctor", doctor);
        intent.putExtra("appointmentTime", unixTime);
        Log.d("TIME", "/ " + z.toString() + " /");
        startActivity(intent);
    }


    private class ServicesAdapter extends BaseAdapter {

        private ArrayList<Service> services;
        private TextView txtTotalCost;

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

        public ServicesAdapter(ArrayList<Service> services, TextView txtTotalCost) {
            this.services = services;
            this.txtTotalCost = txtTotalCost;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_spinner_service, container, false);
            }
            LinearLayout serviceInfoView = (LinearLayout) convertView;
            Service service = this.services.get(position);
            ((TextView) serviceInfoView.findViewById(R.id.txtServiceName)).setText(service.getServiceName());
            TextView textServiceCost = serviceInfoView.findViewById(R.id.txtServiceCost);
            textServiceCost.setText("" + service.getPrice() + " €");
            ImageView imgCheck = serviceInfoView.findViewById(R.id.imgServiceCheck);

            if (selectedServices[position] == 1) {
                ((ImageView) serviceInfoView.findViewById(R.id.imgServiceCheck)).setImageResource(R.drawable.check_filled);
                textServiceCost.setTextColor(getResources().getColor(R.color.colorAccent));
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
                        selectedServices[position] = 1;
                        totalCost += service.getPrice();
                        textServiceCost.setTextColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        imgCheck.setImageResource(R.drawable.check_empty);
                        imgCheck.setTag(0);
                        selectedServices[position] = 0;
                        totalCost -= service.getPrice();
                        textServiceCost.setTextColor(Color.GRAY);
                    }
                    txtTotalCost.setText(totalCost + " €");
                }
            });

            serviceInfoView.startAnimation(AnimationUtils.loadAnimation(AppointmentsActivity.this, R.anim.animation_list_scroll));
            return serviceInfoView;
        }
    }

}
