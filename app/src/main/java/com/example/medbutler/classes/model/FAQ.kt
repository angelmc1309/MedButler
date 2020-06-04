package com.example.medbutler.classes.model

import java.io.Serializable

class FAQ : Serializable {
    var question:String=""
    var answerr:String=""
    constructor(){

    }
    constructor(question:String, answer:String){
        this.question=question
        this.answerr=answer
    }
    fun setQuestio(question:String){
        this.question=question
    }
    fun setAnswer(answer:String){
        this.answerr=answer
    }
    fun getQuestio(): String {
        return this.question
    }
    fun getAnswer():String{
        return this.answerr
    }
}