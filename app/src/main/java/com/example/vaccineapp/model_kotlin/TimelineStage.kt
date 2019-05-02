package com.example.vaccineapp.model_kotlin

class TimelineStage {

    private val timelineGroup: ArrayList<InfoElement> = arrayListOf()

    fun addInfoElement(info: InfoElement){
        timelineGroup.add(info)
    }

    fun getInfoElement(position: Int){
        timelineGroup.get(position)
    }

    fun getSize(){
        timelineGroup.size
    }
}