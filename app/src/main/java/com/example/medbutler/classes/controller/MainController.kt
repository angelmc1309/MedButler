package com.example.medbutler.classes.controller

import android.widget.TextView
import com.example.medbutler.R
import com.example.medbutler.UserProfile
import com.example.medbutler.classes.dataBase.DAOUser
import com.example.medbutler.classes.model.Usuari


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
    fun login(username:String, passwor: String){
        daoUser.login(username,passwor)
    }
    fun signOut(){
        daoUser.signOut()
    }
    fun initUserProfer(context:UserProfile){
        context.findViewById<TextView>(R.id.userName_label).setText("Fullname: "+getcurrent().getfullname())
        context.findViewById<TextView>(R.id.email_label).setText("Email:"+getcurrent().getusername())
        context.findViewById<TextView>(R.id.date_label).setText("Birthday: "+getcurrent().getbirthday())
        context.findViewById<TextView>(R.id.height_label).setText( "Height: "+getcurrent().getheight())
        context.findViewById<TextView>(R.id.weight_label).setText( "Weight: "+getcurrent().getweight())
        context.findViewById<TextView>(R.id.user_complete_name_label).setText(getcurrent().getfullname())
    }

    fun Enregistrar(user: Usuari, passwor: String) {
    daoUser.save(user,passwor)
}
    fun addMed(
    id: String, name: String, period: Int, duration: Int, startTimeMinute: Int,
    startTimeHour: Int, allowNotification: Boolean
    ) {
    user.addMed(id, name, period, duration, startTimeMinute, startTimeHour,
    allowNotification)

    }
    fun getMedList():String{
    return  user.getMedList()
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
        daoUser.updatefullname("prova")
    }
    fun changeHeight(newheight:String){
        daoUser.updatefullname("prova")
    }
    fun changeWeight(newweight:String){
        daoUser.updatefullname("prova")
    }
    fun changeBirthday(newbirthday:String){
        daoUser.updatefullname("prova")
    }

}











