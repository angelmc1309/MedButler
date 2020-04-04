package com.example.medbutler.classes.dataBase

interface DAO<T> {

    fun get(id:String)
    fun getAll()
    fun save(obj:T)
    fun update(obj:T,params:List<String>)
    fun delete(obj:T)

}