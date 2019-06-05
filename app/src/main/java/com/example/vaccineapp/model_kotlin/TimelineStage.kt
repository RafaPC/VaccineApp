package com.example.vaccineapp.model_kotlin

class TimelineStage {

    private val timelineGroup: ArrayList<InfoElement> = arrayListOf()

    fun addInfoElement(info: InfoElement){
        timelineGroup.add(info)
    }

    fun getInfoElement(position: Int): InfoElement = timelineGroup.get(position)

    val size: Int
    get() = timelineGroup.size
}