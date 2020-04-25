package com.example.medbutler.classes.model

object AllDiseases {

    val list = arrayListOf<Disease>(
        Disease("hipertension_arterial","Hipertensión arterial"),Disease("infarto_de_miocardio","Infarto de miocardio"),
        Disease("lumbalgia","Lumbalgia"), Disease("artritis","Artritis (incluida la artrosis)"), Disease("colesterol_alto","Hipercolesterolemia (colesterol alto)"),
        Disease("alergia_cronica","Alergia crónica"), Disease("cefaleas","Cefaleas"), Disease("diabetes","Diabetes"),Disease("cancer_rinon","Cáncer de riñón"),
        Disease("cancer_vias_biliares","Cáncer de las vías biliares"),Disease("cancer_uretra","Cáncer de uretra"),Disease("cancer_laringe","Cáncer de laringe"),
        Disease("ansiedad_cronica","Ansiedad crónica"), Disease("bronquitis_cronica","Bronquitis crónica"), Disease("parkinson","Parkinson"),
        Disease("osteoporosis","Osteoporosis"), Disease("vih","VIH / SIDA"), Disease("enfermedad_pulmonar_obstructiva_cronica","Enfermedad Pulmonar Obstructiva Crónica"),
        Disease("depresion","Depresión"), Disease("hipertiroidismo","Hipertiroidismo"),Disease("hipotiroidismo","Hipotiroidismo"),  Disease("alzheimer","Alzheimer"),
        Disease("esclerosis_multiple","Esclerosis múltiple"))

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
            aux.add(item.toString())
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