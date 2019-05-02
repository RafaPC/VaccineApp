package com.example.vaccineapp.model_kotlin

import com.example.vaccineapp.model.Appointment
import com.example.vaccineapp.model.Profile

object ProfilesManager {

    var indexProfile: Int = 0
    var profiles: ArrayList<Profile> = arrayListOf()

    fun getProfile(): Profile = profiles.get(indexProfile)

    fun getAllAppointments(): ArrayList<Appointment> {
        val appointments = arrayListOf<Appointment>()
        for (profile in profiles) {
            appointments.addAll(profile.appointments)
        }
        return appointments
    }
}