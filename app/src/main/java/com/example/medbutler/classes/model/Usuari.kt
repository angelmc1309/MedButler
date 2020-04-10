package com.example.medbutler.classes.model

open class Usuari {
    private lateinit var username:String
    private lateinit var passwrd:String
    private lateinit var birthday:String
    private lateinit var gender:String
    private lateinit var weight:String
    private lateinit var height:String
    private lateinit var fullname:String
    constructor(username:String,fullname:String, birthday:String, height:String,weight:String, gender:String){
        this.username=username
       // this.passwrd=password
        this.birthday=birthday
        this.gender=gender
        this.height=height
        this.weight=weight
        this.fullname=fullname
    }
    fun getId():String{
        return this.username
    }
    fun getFullname():String{
        return this.fullname
    }
    fun getPassword():String{
        return this.passwrd
    }
    fun getBirthday():String{
        return this.birthday
    }
    fun getHeight():String{
        return this.height
    }
    fun getWeight():String{
        return this.weight
    }
    fun setId(newMail:String){
        this.username=newMail
    }
    fun setFullname(newFullname:String){
        this.fullname=newFullname
    }
    fun setPassword(newPass:String){
        this.passwrd=newPass
    }
    fun setBirthday(newBirth:String){
        this.birthday=newBirth
    }
    fun setWeight(newWeight:String){
        this.weight=newWeight
    }
    fun setHeight(newHeight:String){
        this.height=newHeight
    }

    //var d=Usuari("k","j","s","s")

}