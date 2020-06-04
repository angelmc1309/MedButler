package com.example.medbutler.classes.dataBase

import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.FAQ
import com.example.medbutler.classes.model.faqFacade
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.MainScope

class DAOFaq: DAO<FAQ>  {

    var faqdb = FirebaseFirestore.getInstance().collection("faqs")
    override fun get(id: String) {
    }

    override fun getAll() {
    }

    override fun save(obj: FAQ) {
    }

    override fun update(obj: FAQ, params: List<String>) {
    }

    override fun delete(obj: FAQ) {
    }
    fun initFAQFacade(){
        var faqFacade=faqFacade()
        faqdb.get().addOnCompleteListener{ task->
            if(task.isSuccessful){
                for(document in task.result!!){
                    var faq=FAQ(document.data["questio"].toString(),document.data["answerr"].toString())
                    faqFacade.addFaq(faq)
                }
                MainController.setFAQFacade(faqFacade)
            }

        }
    }
}