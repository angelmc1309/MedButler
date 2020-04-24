package com.example.medbutler.classes.controller

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import android.widget.TextView
import com.example.medbutler.Login
import com.example.medbutler.R
import com.example.medbutler.UserProfile
import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


object MainController {
    /*Unique instance of the controller*/
    //object Singleton

    var user:Usuari = Usuari()
    val daoUser = DAOUser()
    //var db = FirebaseFirestore.getInstance()

    fun setCurrentUser(user:Usuari){
        this.user=user

    }
    fun getcurrent():Usuari{
        return this.user
    }
    fun initFirestore(){
        daoUser.get()
    }
    fun deleteUser(email:String){
        daoUser.delete(email)
    }
    fun login(context: Login, username:String, passwor: String) {
        daoUser.login(context,username,passwor)
    }
    fun signOut(){
        daoUser.signOut()
    }
    fun initUserProfer(context:UserProfile){

        context.findViewById<TextView>(R.id.user_complete_name_label).text = getcurrent().getfullname()
        context.findViewById<TextView>(R.id.email_label).text = context.getString(R.string.email,getcurrent().getusername())
        context.findViewById<TextView>(R.id.date_label).text = context.getString(R.string.birthday,getcurrent().getbirthday())
        context.findViewById<TextView>(R.id.height_label).text = context.getString(R.string.height2,getcurrent().getheight(), "cm")
        context.findViewById<TextView>(R.id.weight_label).text = context.getString(R.string.weight2,getcurrent().getweight(),"kg")
    }

    fun Enregistrar(user: Usuari, passwor: String) {
        daoUser.save(user,passwor)
    }
    fun saveListMed() {
        daoUser.saveListMed()
    }
    fun addMed(id: String, name: String, period: Int, duration: Int, startTimeMinute: Int,
               startTimeHour: Int, allowNotification: Boolean) {
        user.addMed(id, name, period, duration, startTimeMinute, startTimeHour,
            allowNotification)
    }

    fun getMedList():String{
        return  user.getMedList()
    }
    fun getMedListArray(): ArrayList<Med> {
        return user.getMedListArray()
    }
    fun removeMed(id: String){
        user.removeMed(id)
    }

    fun changePassword(newPassword:String){
        daoUser.changePassword(newPassword)
    }
    fun changeEmail(newEmail:String){
        daoUser.changeEmail(newEmail)
    }
    fun changeFullname(newfullname:String){
        daoUser.updatefullname(newfullname)
    }
    fun changeHeight(newheight:String){
        daoUser.updateheight(newheight)
    }
    fun changeWeight(newweight:String){
        daoUser.updateweight(newweight)
    }
    fun changeBirthday(newbirthday:String){
        daoUser.updatedate(newbirthday)
    }

    //order enter que indica quin apat del dia es
    fun setFood(date:String,order:Int,name: String) {
        // test this funtionality
        if(existsThisDay(date)) {
            user.setFood(date,order,name)
        }else{
            user.addDay(date)
            user.setFood(date, order, name)
        }
    }


    fun existsThisDay(date:String):Boolean{
        return user.existsThisDay(date)
    }

    fun createDay(date: String){
        if(!existsThisDay(date)){
            user.addDay(date)
        }
    }
    fun getDayList():String{
        return user.getDayList()
    }

    fun removeDay(date: String){
        user.removeDay(date)
    }

    fun getFood(date:String):String?{
        if(existsThisDay(date)){
            return user.getFood(date)
        }else{
            return null
        }
    } //Preguntar com solucionar lo del interrogant
    fun addUserDisease(disease:String){
        user.addDisease(disease)
    }
    fun removeUserDisease(disease: String){
        user.removeDisease(disease)
    }
    fun showUserDisease():String{
        return user.showDiseaseIHave()
    }
    fun addDatabaseDisease(disease: String){
        Disease.addNewDisease(disease)
    }
    fun removeDatabaseDisease(disease: String){
        Disease.removeDisease(disease)
    }
    fun showAllDatabaseDisease():String{
        return Disease.toString()
    }
}






