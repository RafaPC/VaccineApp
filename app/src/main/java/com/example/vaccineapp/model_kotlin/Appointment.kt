package com.example.vaccineapp.model

import org.joda.time.LocalDateTime

import java.util.ArrayList

class Appointment(val reason: String, val hospital: String //This could be an id to identify the hospital
                  , val doctor: String //The same for this field too
                  , val date: LocalDateTime, val services: ArrayList<Service>) {

    val dateString: String
        get() = date.toString("dd - MMM - YYYY")

    val timeString: String
        get() = date.toString("hh:mm aa")

    //CAMBIAR EL EQUALS

    val profile: Profile?
        get() {
            for (profile in ProfilesManager.profiles) {
                for (appointment in profile.appointments) {
                    if (this == appointment) {
                        return profile
                    }
                }
            }
            return null
        }

    val servicesCost: Int
        get() {
            var cost = 0

            for (service in this.services) {
                cost += service.price
            }

            return cost
        }

    override fun equals(o: Any?): Boolean = o === this

    val alreadyExists: Boolean
    get() {
        val exists = false
        for (profile in ProfilesManager.profiles) {
            for (appointment in profile.appointments) {
                if (this == appointment) {
                    return true
                }
            }
        }
        return exists
    }
}
