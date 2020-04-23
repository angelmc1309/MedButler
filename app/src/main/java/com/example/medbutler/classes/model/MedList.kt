package com.example.medbutler.classes.model

object MedList{

    val list :ArrayList<Med> = ArrayList<Med>()
        get() = field
    fun addMed(id: String,name: String, period: Int,duration: Int,startTimeMinute:Int,
               startTimeHour:Int,allowNotification:Boolean){

        val med = Med(id, name, period, duration, startTimeMinute,
            startTimeHour, allowNotification)
        list.add(med)
    }

    override fun toString(): String {
        return list.toString()
    }

    fun removeMed(id: String) {
        for(med in list){
            if (id == med.id){
                list.remove(med)
                return
            }
        }
    }
    fun rtsList(): ArrayList<Med> {
        return list
    }
}