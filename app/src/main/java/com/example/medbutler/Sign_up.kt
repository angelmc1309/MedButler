package com.example.medbutler

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Disease
import com.example.medbutler.classes.model.Usuari
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.user_profile_layout.*
import java.nio.charset.MalformedInputException
import java.util.*
import java.util.Calendar

class Sign_up : AppCompatActivity() {


    var aux :ArrayList<Disease> = ArrayList<Disease>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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
        val mostrarDate=DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, lany, lmes, ldia -> birthdayRegist.setText(""+ldia+"/"+(lmes.toInt()+1).toString()+"/"+lany)} , any, mes, dia)
        mostrarDate.show()
    }
    fun enregistrar(view: View){
        var genderr:String=""

        if (! passwordRegist1.text.toString().equals(passwordRegist2.text.toString())){
            Toast.makeText(this,"Contresenya diferent", Toast.LENGTH_SHORT).show()
        }
        if(passwordRegist1.text.toString().isEmpty()){
            passwordRegist1.error="Camp obligatori!"
            passwordRegist1.requestFocus()
            return
        }
        if(passwordRegist2.text.toString().isEmpty()){
            passwordRegist2.error="Camp obligatori!"
            passwordRegist2.requestFocus()
            return
        }
        if(passwordRegist1.length()<6){
            passwordRegist1.error="Mínim 6 caracters!"
            passwordRegist1.requestFocus()
            return
        }
        if(birthdayRegist.toString().isEmpty()){
            birthdayRegist.error="Camp obligatori"
            birthdayRegist.requestFocus()
            return
        }
        if(radioGroupGender.checkedRadioButtonId==-1){
            Toast.makeText(this, "Home o dona?",Toast.LENGTH_SHORT).show()
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usernameRegist.text.toString()).matches()){
            usernameRegist.error="Ha de ser un email!"
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
            pesRegist.error="Camp obligatori"
            pesRegist.requestFocus()
        }
        if(alturaRegist.toString().isEmpty()){
            alturaRegist.error="Camp obligatori"
            alturaRegist.requestFocus()
        }
        if(pesRegist.toString().isEmpty()){
            pesRegist.error="Camp obligatori"
            pesRegist.requestFocus()
        }



        val user:Usuari=
            Usuari(usernameRegist.text.toString(),fullnameRegist.text.toString(),
                birthdayRegist.text.toString(),alturaRegist.text.toString(),pesRegist.text.toString())
        user.listOfDisease=aux
        MainController.Enregistrar(user, passwordRegist1.text.toString())
        val intent= Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
    fun selectDisease(view: View){
        val builder=AlertDialog.Builder(this@Sign_up)
        builder.setTitle("Select your diseases")
        val arrayDisease=MainController.rtStringArrayAllDisease()

        val arrayDisease2=MainController.rtStringArrayAllDisease2()
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




















