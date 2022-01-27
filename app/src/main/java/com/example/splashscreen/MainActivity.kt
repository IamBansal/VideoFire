package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This Looper.getMainLooper is used because Handler() is deprecated.
        Handler(Looper.getMainLooper()).postDelayed({startActivity(Intent(this, LoginPage::class.java))}, 2000)

    }
}