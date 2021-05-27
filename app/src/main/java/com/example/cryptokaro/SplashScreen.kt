package com.example.cryptokaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val b : View = findViewById(R.id.contBtn_splash_view)
        b.setOnClickListener {
            startActivity(Intent(this@SplashScreen,MainActivity::class.java))
        }



    }
}