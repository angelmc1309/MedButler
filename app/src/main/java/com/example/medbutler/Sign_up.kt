package com.example.medbutler

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.Calendar

class Sign_up : AppCompatActivity() {

    private lateinit var firebase_auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebase_auth=FirebaseAuth.getInstance()
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
            Toast.makeText(this, "Ets un crack!",Toast.LENGTH_SHORT).show()
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usernameRegist.text.toString()).matches()){
            usernameRegist.error="Ha de ser un email!"
            usernameRegist.requestFocus()
            return

        }
        firebase_auth.createUserWithEmailAndPassword(usernameRegist.text.toString(),passwordRegist1.text.toString())
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    val intent= Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    var e:FirebaseAuthException= task.exception as FirebaseAuthException

                    Toast.makeText(baseContext,e.message.toString(),Toast.LENGTH_LONG).show()

                }
            }



    }

}




















