package com.example.medbutler.classes.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Disease
import com.example.medbutler.classes.model.Usuari
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*
import java.util.Calendar

class Sign_up : AppCompatActivity() {


    var aux :ArrayList<Disease> = ArrayList<Disease>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val register: TextView = findViewById(R.id.butRegist)
        val log: TextView = findViewById(R.id.labelFerLog)

        register.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_clear)))
        register.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()

        log.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.grey_transp)))
        log.setBackgroundResource(R.drawable.rounded_button)
        //addBut.setTextColor()
    }
    fun actionGoToLogin(view: View){
        val intent= Intent(this, Login::class.java)
        startActivity(intent)
    }
    fun pickBirthday(view: View){
        val calendari=java.util.Calendar.getInstance()
        val any=calendari.get(java.util.Calendar.YEAR)
        val mes=calendari.get(java.util.Calendar.MONTH)
        val dia=calendari.get(Calendar.DAY_OF_MONTH)
        val mostrarDate=DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, lany, lmes, ldia ->
            val selectedDate = ldia.toString() + " / " + (lmes + 1) + " / " + lany
            birthdayRegist.setText(selectedDate)} , any, mes, dia)
        mostrarDate.show()
    }
    fun enregistrar(view: View){
        var genderr:String=""

        if (! passwordRegist1.text.toString().equals(passwordRegist2.text.toString())){
            Toast.makeText(this,"Different password", Toast.LENGTH_SHORT).show()
        }
        if(passwordRegist1.text.toString().isEmpty()){
            passwordRegist1.error="Required field!"
            passwordRegist1.requestFocus()
            return
        }
        if(passwordRegist2.text.toString().isEmpty()){
            passwordRegist2.error="Required field!"
            passwordRegist2.requestFocus()
            return
        }
        if(passwordRegist1.length()<6){
            passwordRegist1.error="Min 6 characters!"
            passwordRegist1.requestFocus()
            return
        }
        if(birthdayRegist.toString().isEmpty()){
            birthdayRegist.error="Required field"
            birthdayRegist.requestFocus()
            return
        }
        if(radioGroupGender.checkedRadioButtonId==-1){
            Toast.makeText(this, "Man or woman?",Toast.LENGTH_SHORT).show()
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usernameRegist.text.toString()).matches()){
            usernameRegist.error="It must be an email!"
            usernameRegist.requestFocus()
            return
        }
        /*if(femaleButRegst.isChecked){
            genderr="Female"
        }else if(maleButRegist.isChecked){
            genderr="Male"
        }else{
            Toast.makeText(this,"Man or Women!!!", Toast.LENGTH_SHORT).show()
        }*/
        if(pesRegist.toString().isEmpty()){
            pesRegist.error="Required field"
            pesRegist.requestFocus()
        }
        if(alturaRegist.toString().isEmpty()){
            alturaRegist.error="Required field"
            alturaRegist.requestFocus()
        }
        if(pesRegist.toString().isEmpty()){
            pesRegist.error="Required field"
            pesRegist.requestFocus()
        }



        val user:Usuari=
            Usuari(usernameRegist.text.toString(),fullnameRegist.text.toString(),
                birthdayRegist.text.toString(),alturaRegist.text.toString(),pesRegist.text.toString())
        user.listOfDisease = aux

        MainController.Enregistrar(this,user, passwordRegist1.text.toString())

    }
    fun selectDisease(view: View){
        val builder=AlertDialog.Builder(this@Sign_up)
        builder.setTitle("Select your diseases")
        val arrayDisease=MainController.rtStringArrayAllDisease()

        val arrayDisease2 = MainController.rtStringArrayAllDisease2()
        arrayDisease.set(arrayDisease.size-1, "None of the above disease")
        arrayDisease2.set(arrayDisease2.size-1, Disease("None of the above disease","none of the above disease"))
        val checkedDeseaseArray= BooleanArray(arrayDisease.size)
        builder.setMultiChoiceItems(arrayDisease, checkedDeseaseArray){dialog, which, isChecked ->
            checkedDeseaseArray[which]=isChecked
        }
        builder.setPositiveButton("Ok"){dialog, which ->
            aux.clear()
            for(i in arrayDisease.indices){
                val checkedd=checkedDeseaseArray[i]
                if(checkedd){
                    aux.add(arrayDisease2[i])

                }
            }
            for(d in aux){
                if(d.id.equals("None of the above disease")){

                    contactwithAdmin()
                    aux.remove(d)
                }
            }
        }
        builder.setNeutralButton("Cancel"){dialog, which ->
            dialog.dismiss()
        }

        val dial:AlertDialog=builder.create()
        dial.show()
}
    fun contactwithAdmin(){
        val builder=AlertDialog.Builder(this@Sign_up)
        builder.setTitle("Tell us your disease")
        var newDs:EditText= EditText(this)
        newDs.hint="We'll update your disease as soon as possible"
        newDs.inputType=InputType.TYPE_CLASS_TEXT
        builder.setView(newDs)
        builder.setPositiveButton("Ok"){dialog, which ->
            MainController.afegirNewDisease(Disease(newDs.text.toString(),newDs.text.toString()))
        }
        builder.setNeutralButton("Cancel"){dialog, _ ->
            dialog.dismiss()
        }
        val dial:AlertDialog=builder.create()
        dial.show()
    }
}




















