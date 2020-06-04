package com.example.medbutler.classes.model

class faqFacade {
    var list :ArrayList<FAQ> = ArrayList<FAQ>()
    constructor(){

    }
    constructor(list:ArrayList<FAQ>){
        this.list=list
    }
    fun addFaq(faq:FAQ){
        list.add(faq)
    }
    fun removeFaq(faq:FAQ){
        list.remove(faq)
    }
    fun setListFAQ(list: ArrayList<FAQ>){
        this.list=list
    }
    fun getListFAQ():ArrayList<FAQ>{
        return this.list
    }
}