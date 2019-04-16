package com.example.vaccineapp.model;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int color;
    private String birthdate;
    private ArrayList<String> information;
    private ArrayList<TimelineStage> timeline;
    private ArrayList<Appointment> appointments;
    private int age;
    private int children;

    public Profile(String name, int color, String birthdate, int age, int children) {
        this.name = name;
        this.color = color;
        this.birthdate = birthdate;
        this.age = age;
        this.children = children;
        information = new ArrayList<>();
        timeline = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getChildren() {
        return children;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getInformation() {
        return information;
    }

    public void addInformation(String information) {
        this.information.add(information);
    }

    public ArrayList<TimelineStage> getTimeline() {
        return timeline;
    }

    public void addTimelineStage(TimelineStage stage) {
        this.timeline.add(stage);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<InfoElement> getReminders() {
        ArrayList<InfoElement> reminders = new ArrayList<>();

        //Loops throught timeline stages
        for (int i = 0; i < this.timeline.size(); i++) {
            TimelineStage timelineStage = this.timeline.get(i);
            //If the first element isn't checked loops throught the infoElements within the stage
            if (timelineStage.getInfoElement(0).getAlert() != 1) {
                for (int j = 1; j < timelineStage.getSize(); j++) {
                    InfoElement infoElement = timelineStage.getInfoElement(j);
                    //If an infoElement isn't checked it is added in order to be returned
                    if (infoElement.getAlert() != 1) {
                        reminders.add(infoElement);
                    }
                }
            }
        }

        return reminders;
    }

    public void checkReminder(int position) {
        int count = 0;
        for (int i = 0; i < this.timeline.size(); i++) {
            TimelineStage timelineStage = this.timeline.get(i);
            //If the first element isn't checked loops throught the infoElements within the stage
            if (timelineStage.getInfoElement(0).getAlert() != 1) {
                boolean checked = true;
                for (int j = 1; j < timelineStage.getSize(); j++) {
                    InfoElement infoElement = timelineStage.getInfoElement(j);
                    //If an infoElement isn't checked it is added in order to be returned
                    if (infoElement.getAlert() != 1) {
                        if (count == position) {
                            infoElement.checkInfo();
                        }
                        count++;
                        if (infoElement.getAlert() != 1) {
                            checked = false;
                        }
                    }
                }
                if (checked) {
                    timelineStage.getInfoElement(0).checkInfo();
                }
            }
        }
    }


    public ArrayList<Appointment> getAppointmentsFromDate(int year, int month, int dayOfMonth) {
        ArrayList<Appointment> appointments = new ArrayList<>();

        for (int i = 0; i < this.appointments.size(); i++) {
            Appointment appointment = this.appointments.get(i);
            LocalDate date = appointment.getDate().toLocalDate();
            if (date.getYear() == year && date.getMonthOfYear() == month && date.getDayOfMonth() == dayOfMonth) {
                appointments.add(appointment);
            }
        }

        return appointments;
    }
}
