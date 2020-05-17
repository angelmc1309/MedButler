package com.example.medbutler.classes.controller

import android.os.Message
import android.widget.TextView
import com.example.medbutler.classes.view.Login
import com.example.medbutler.R
import com.example.medbutler.classes.view.UserProfile
import com.example.medbutler.classes.dataBase.DAODiseases
import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.*
import com.example.medbutler.classes.view.Sign_up
import java.util.Calendar


object MainController {
    /*Unique instance of the controller*/
    //object Singleton
    var notMessage: String = ""
    var user:Usuari = Usuari()
    val daoUser = DAOUser()
    lateinit var  thrower:NotificationThrower
    //var db = FirebaseFirestore.getInstance()

    fun setCurrentUser(user:Usuari){
        this.user = user
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
    fun initUserProfer(context: UserProfile){

        context.findViewById<TextView>(R.id.user_complete_name_label).text = getcurrent().getfullname()
        context.findViewById<TextView>(R.id.email_label).text = context.getString(R.string.email,getcurrent().getusername())
        context.findViewById<TextView>(R.id.date_label).text = context.getString(R.string.birthday,getcurrent().getbirthday())
        context.findViewById<TextView>(R.id.height_label).text = context.getString(R.string.height2,getcurrent().getheight(), "cm")
        context.findViewById<TextView>(R.id.weight_label).text = context.getString(R.string.weight2,getcurrent().getweight(),"kg")
    }

    fun Enregistrar(context: Sign_up, user: Usuari, passwor: String) {
        daoUser.save(context,user,passwor)
    }
    fun saveUserAll() {
        daoUser.saveUserAll()
    }
    fun addMed(id: String, name: String, period: Int, duration: Int, startTimeMinute: Int,
               startTimeHour: Int, allowNotification: Boolean) {
        user.addMed(id, name, period, duration, startTimeMinute, startTimeHour,
            allowNotification)

            val c: Calendar = Calendar.getInstance()
            c.set(Calendar.HOUR_OF_DAY, startTimeHour)
            c.set(Calendar.MINUTE, startTimeMinute)
            c.set(Calendar.SECOND, 0)
            setGenericNotification(c, name)

    }

    fun getMedListString():String{
        return  user.getMedListString()
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
    fun deleteFood(date:String, number:Int){
        user.deleteFood(date,number)
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

    fun getFoodArray(date:String): ArrayList<Food>? {
        if(existsThisDay(date)){
            return user.getFoodArray(date)
        }else{
            return null
        }
    }
    fun getReminderArray(date:String): ArrayList<Reminder>? {
        if(existsThisDay(date)){
            return user.getReminderArray(date)
        }else{
            return null
        }
    }
    fun getTaskArray(date:String): ArrayList<Task>? {
        if(existsThisDay(date)){
            return user.getTaskArray(date)
        }else{
            return null
        }
    }
    fun getFood(date:String):String?{
        if(existsThisDay(date)){
            return user.getFood(date)
        }else{
            return null
        }
    } //Preguntar com solucionar lo del interrogant
    fun addUserDisease(disease:Disease){
        user.addDisease(disease)
    }
    fun removeUserDisease(disease: Disease){
        user.removeDisease(disease)
    }
    fun addDatabaseDisease(disease: Disease){
        DAODiseases.addNewDisease(disease)
    }
    fun removeDatabaseDisease(disease: Disease){
        DAODiseases.removeDisease(disease)
    }
    fun showAllDatabaseDisease():String{
        return DAODiseases.toString()
    }
    fun getAllDatabaseDisease(): ArrayList<Disease> {
        return DAODiseases.list
    }
    fun rtStringArrayAllDisease(): Array<String> {

        return DAODiseases.rtArrayList()
    }
    fun rtStringArrayAllDisease2(): Array<Disease> {

        return DAODiseases.rtArrayList2()
    }
    fun afegirNewDisease(disease: Disease){
        daoUser.afegirNewDisease(disease)
    }
    fun addReminder(id: String, reminderDate: String, reminderName: String, importance: Int,allowNotification:Boolean){
        user.addReminder(id,reminderDate,reminderName,importance,allowNotification)

    }
    fun addTask(id: String, taskDate: String, taskName: String, taskStartTimeMinute: Int,taskStartTimeHour:Int, allowNotification:Boolean){
        user.addTask(id,taskDate,taskName,taskStartTimeMinute,taskStartTimeHour,allowNotification)
        if(allowNotification) {
            val c: Calendar = Calendar.getInstance()
            c.set(Calendar.HOUR_OF_DAY, taskStartTimeHour)
            c.set(Calendar.MINUTE, taskStartTimeMinute)
            c.set(Calendar.SECOND, 0)
            setGenericNotification(c, taskName)
        }
    }
    fun removeReminder(reminderDate: String, id: String) {
        user.removeReminder(reminderDate,id)
    }
    fun removeTask(taskDate: String, id: String) {
        user.removeTask(taskDate,id)
    }
    fun setGenericNotification(c:Calendar,message: String){
        thrower.updateTimeText(message)
        thrower.startAlarm(c)

    }

    fun setNotificationThrower(n: NotificationThrower) {
        thrower = n

    }
}






