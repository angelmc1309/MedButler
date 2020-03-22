package com.example.medbutler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(4000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }
}
