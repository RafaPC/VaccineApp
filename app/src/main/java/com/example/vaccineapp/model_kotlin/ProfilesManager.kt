package com.example.vaccineapp.model_kotlin

object ProfilesManager {

    var indexProfile: Int = 0
    var profiles: ArrayList<Profile> = arrayListOf()

    fun getProfile(): Profile = profiles.get(indexProfile)
    fun addProfile(profile: Profile): Any = profiles.add(profile)

    fun getAllAppointments(): ArrayList<Appointment> {
        val appointments = arrayListOf<Appointment>()
        for (profile in profiles) {
            appointments.addAll(profile.appointments)
        }
        return appointments
    }
}