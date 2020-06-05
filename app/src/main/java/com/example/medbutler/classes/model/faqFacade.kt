package com.example.medbutler.classes.model

class faqFacade {
    var list :ArrayList<String> = ArrayList<String>()
    constructor(){

    }
    constructor(list:ArrayList<String>){
        this.list=list
    }
    fun addFaq(faq:String){
        list.add(faq)
    }
    fun removeFaq(faq:String){
        list.remove(faq)
    }
    fun setListFAQ(list: ArrayList<String>){
        this.list=list
    }
    fun getListFAQ():ArrayList<String>{
        return this.list
    }
}