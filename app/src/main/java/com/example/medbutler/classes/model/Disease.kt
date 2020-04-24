package com.example.medbutler.classes.model

object Disease {

    val list = arrayListOf<String>("Hipertensión arterial", "Enfermedades cardiovasculares / Enfermedad del corazón (como infartos)", "Lumbalgia",
        "Artritis (incluida la artrosis)", "Colesterol alto", "Alergia crónica", "Cefaleas", "Diabetes", " Cáncer", " Ansiedad crónica",
        "Bronquitis crónica", "Parkinson", "Osteoporosis", "VIH / SIDA", "Enfermedad Pulmonar Obstructiva Crónica", "Colesterol",
        "Depresión", "Ansiedad", "Tiroides", "Alzheimer", "Esclerosis múltiple", "Hipertensión")
        get() = field
    fun addNewDisease(newDisease:String){
        list.add(newDisease)
    }
    fun removeDisease(disease: String){
        list.remove(disease)
    }
    fun search(disease: String): Boolean {
        if(disease in list){
            return true
        }else{
            addNewDisease(disease)
            return false
        }
    }
}