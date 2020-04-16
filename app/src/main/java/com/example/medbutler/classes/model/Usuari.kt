package com.example.medbutler.classes.model

 class Usuari
 {
     private var username: String=""
     private var fullname: String=""
     private var birthday: String=""
     private var height: String=""
     private var weight: String=""
     private val medList:MedList = MedList()
     private val appearanceInfo:UserAppearenceInfo = UserAppearenceInfo()
     //private var gender: String=""
     constructor(){

     }
     constructor(username: String,
                  fullname: String,
                 birthday: String,
                 height: String,
                 weight: String
                 ){
         this.username=username
         this.fullname=fullname
         this.birthday=birthday
         this.height=height
         this.weight=weight
         //gender: String
         //this.gender=gender
     }

    fun tostringg():String{
        return username+" "+fullname+" "+birthday+" "+height+" "+weight
                //gender
    }
    fun getusername(): String? {
        return this.username
    }
    fun getfullname(): String {
        return this.fullname
    }

    fun getbirthday(): String? {
        return this.birthday
    }
    fun getheight(): String? {
        return this.height
    }
    fun getweight(): String? {
        return this.weight
    }
    fun setusername(newMail:String){
        this.username=newMail
    }
    fun setfullname(newFullname:String){
        this.fullname=newFullname
    }

    fun setbirthday(newBirth:String){
        this.birthday=newBirth
    }
    fun setweight(newWeight:String){
        this.weight=newWeight
    }
    fun setheight(newHeight:String){
        this.height=newHeight
    }
     fun addMed(id: String,name: String, period: Int,duration: Int,startTimeMinute:Int,
                startTimeHour:Int,allowNotification:Boolean){
         medList.addMed(id, name, period, duration, startTimeMinute, startTimeHour, allowNotification)
     }

     fun getMedList():String {
         return medList.toString()
     }

     fun removeMed(id: String) {
         medList.removeMed(id)
     }

     fun getappearanceInfo(): UserAppearenceInfo {
        return this.appearanceInfo
     }
    //var d=Usuari("k","j","s","s")


}