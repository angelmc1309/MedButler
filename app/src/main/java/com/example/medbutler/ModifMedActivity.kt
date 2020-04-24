package com.example.medbutler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ModifMedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_med_layout)
        val bundle = intent.extras
        val message = bundle!!.getString("medId")
    }
}