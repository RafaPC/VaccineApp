@file:JvmName("InfoElement")

package com.example.vaccineapp.model_kotlin

import com.example.vaccineapp.R

const val OK = 1;
const val WARNING = -1;
const val DANGER = -2;


class InfoElement (information: String, alertLevel: Int){
    val information: String = information
    private var alertLevel: Int = alertLevel

    fun getAlertResource(): Int{
        return when(alertLevel){
            OK -> R.drawable.check
            WARNING -> R.drawable.warning_yellow
            DANGER -> R.drawable.warning_red
            else -> R.drawable.check
        }
    }

    fun checkInfo(){
        alertLevel = 1
    }





}