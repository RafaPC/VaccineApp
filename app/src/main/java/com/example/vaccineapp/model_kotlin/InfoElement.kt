package com.example.vaccineapp.model_kotlin

import com.example.vaccineapp.R

const val OK = 1
const val WARNING = -1
const val DANGER = -2


class InfoElement (val information: String, var alertLevel: Int){

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