package com.example.medbutler.classes.model

object AllDiseases {

    val list = arrayListOf<Disease>(
        Disease("hipertension_arterial","Hipertensión arterial"),Disease("enfermedades_cardiovasculares","Enfermedades cardiovasculares / Enfermedad del corazón (como infartos)"),
        Disease("lumbalgia","Lumbalgia"), Disease("artritis","Artritis (incluida la artrosis)"), Disease("colesterol_alto","Colesterol alto"),
        Disease("alergia_cronica","Alergia crónica"), Disease("cefaleas","Cefaleas"), Disease("diabetes","Diabetes"),Disease("cancer","Cáncer"),
        Disease("ansiedad_cronica","Ansiedad crónica"), Disease("bronquitis_cronica","Bronquitis crónica"), Disease("parkinson","Parkinson"),
        Disease("osteoporosis","Osteoporosis"), Disease("vih","VIH / SIDA"), Disease("enfermedad_pulmonar_obstructiva_cronica","Enfermedad Pulmonar Obstructiva Crónica"),
        Disease("colesterol","Colesterol"), Disease("depresion","Depresión"), Disease("ansiedad","Ansiedad"), Disease("tiroides","Tiroides"),
        Disease("alzheimer","Alzheimer"), Disease("esclerosis_multiple","Esclerosis múltiple"), Disease("hipertension","Hipertensión"),
        Disease("None of the above disease","none of the above disease"))

    fun addNewDisease(newDisease:Disease){
        list.add(newDisease)
    }
    fun removeDisease(disease: Disease){
        list.remove(disease)
    }
    fun search(disease: Disease): Boolean {
        if(disease in list){
            return true
        }else{
            addNewDisease(disease)
            return false
        }
    }
    fun rtArrayList(): Array<String> {
        val aux :ArrayList<String> = ArrayList<String>()
        for(item in list){
            aux.add(item.id)
        }
        return aux.toTypedArray()
    }
    fun rtArrayList2(): Array<Disease> {
        val aux :ArrayList<Disease> = ArrayList<Disease>()
        for(item in list){
            aux.add(item)
        }
        return aux.toTypedArray()
    }
}