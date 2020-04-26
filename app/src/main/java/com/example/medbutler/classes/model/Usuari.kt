package com.example.medbutler.classes.model

class Usuari
 {
     private var username: String=""
     private var fullname: String=""
     private var birthday: String=""
     private var height: String=""
     private var weight: String=""
     //private var gender: String=""
     var calendar:Calendar = Calendar()
     var medList:MedList = MedList()
     var userAppearenceInfo:UserAppearenceInfo = UserAppearenceInfo()
     //var listOfDisease :ArrayList<Disease> = ArrayList<Disease>()
     var listOfDisease :ArrayList<Disease> = ArrayList<Disease>()

     constructor(){
     }

     constructor(username: String, fullname: String, birthday: String, height: String, weight: String, calendar: Calendar,medList: MedList,
                 userAppearenceInfo: UserAppearenceInfo,listOfDisease: ArrayList<Disease>){
         this.username=username
         this.fullname=fullname
         this.birthday=birthday
         this.height=height
         this.weight=weight
         this.calendar = calendar
         this.medList = medList
         this.userAppearenceInfo = userAppearenceInfo
         this.listOfDisease = listOfDisease
         //gender: String
         //this.gender=gender
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

    fun getusername(): String {
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

     fun getMedListString():String {
         return medList.toString()
     }
     fun getMedListArray(): ArrayList<Med> {
         return medList.list
     }

     fun removeMed(id: String) {
         medList.removeMed(id)
     }

     fun getappearanceInfo(): UserAppearenceInfo {
       // return this.appearanceInfo
         return userAppearenceInfo
     }

    fun setFood(date: String,order:Int,name: String){
        calendar.setFood(date,order,name)
    }

    fun existsThisDay(date: String): Boolean {
        return calendar.existsThisDay(date)
    }

    fun addDay(date: String){
        calendar.addDay(date)
    }

    fun getDayList():String{
        return calendar.toString()
    }

    fun removeDay(date: String){
        calendar.removeDay(date)
    }

    fun getFood(date: String):String?{
        return calendar.getFood(date)
    }
     fun addDisease(disease:Disease){
         listOfDisease.add(disease)
     }
     fun removeDisease(disease: Disease){
         listOfDisease.remove(disease)
     }
}