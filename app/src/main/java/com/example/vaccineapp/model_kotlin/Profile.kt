package com.example.vaccineapp.model_kotlin

import android.content.Context

import com.example.vaccineapp.model_kotlin.InfoElement
import com.example.vaccineapp.model_kotlin.OK
import com.example.vaccineapp.model_kotlin.TimelineStage

import org.joda.time.LocalDate

import java.util.ArrayList
import java.util.Calendar

class Profile(var name: String, val color: Int, var birthdate: Calendar?, val age: Int, val children: Int) {
    val information: ArrayList<String> = ArrayList()
    val timeline: ArrayList<TimelineStage> = arrayListOf<TimelineStage>()
    val appointments: ArrayList<Appointment> = ArrayList()
    var image: Int = 0

    //Loops throught timeline stages
    //If the first element isn't checked loops throught the infoElements within the stage
    //If an infoElement isn't checked it is added in order to be returned
    val reminders: ArrayList<InfoElement>
        get() {
            val reminders = ArrayList<InfoElement>()
            for (timelineStage in this.timeline) {
                if (timelineStage.getInfoElement(0).getAlertResource() != 1) {
                    for (j in 1 until timelineStage.size) {
                        val infoElement = timelineStage.getInfoElement(j)
                        if (infoElement.getAlertResource() != 1) {
                            reminders.add(infoElement)
                        }
                    }
                }
            }

            return reminders
        }


    val birthdateString: String
        get() {
            val date: String
            var day = "" + birthdate!!.get(Calendar.DAY_OF_MONTH)
            if (day.length == 1) {
                day = "0$day"
            }
            var month = "" + (birthdate!!.get(Calendar.MONTH) + 1)
            if (month.length == 1) {
                month = "0$month"
            }

            val year = birthdate!!.get(Calendar.YEAR)
            date = "$day/$month/$year"
            return date
        }

    fun setBirthdate(year: Int, month: Int, day: Int) {
        this.birthdate!!.set(year, month, day)
    }

    fun addInformation(information: String) {
        this.information.add(information)
    }

    fun addTimelineStage(stage: TimelineStage) {
        this.timeline.add(stage)
    }

    fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
    }

    fun checkReminder(position: Int) {
        var count = 0
        for (timelineStage in timeline) {
            //If the first element isn't checked loops throught the infoElements within the stage
            if (timelineStage.getInfoElement(0).getAlertResource() != OK) {
                var checked = true
                for (j in 1 until timelineStage.size) {
                    val infoElement = timelineStage.getInfoElement(j)
                    //If an infoElement isn't checked it is added in order to be returned
                    if (infoElement.getAlertResource() != 1) {
                        if (count == position) {
                            infoElement.checkInfo()
                        }
                        count++
                        if (infoElement.getAlertResource() != 1) {
                            checked = false
                        }
                    }
                }
                if (checked) {
                    timelineStage.getInfoElement(0).checkInfo()
                }
            }
        }
    }

    fun getAppointmentsFromDate(year: Int, month: Int, dayOfMonth: Int): ArrayList<Appointment> {
        val appointments = ArrayList<Appointment>()

        for (appointment in this.appointments) {
            val date = appointment.date.toLocalDate()
            if (date.getYear() == year && date.getMonthOfYear() == month && date.getDayOfMonth() == dayOfMonth) {
                appointments.add(appointment)
            }
        }

        return appointments
    }
}
