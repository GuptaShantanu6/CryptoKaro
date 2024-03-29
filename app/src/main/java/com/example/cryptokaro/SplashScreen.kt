package com.example.cryptokaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.appOrange)

        val b : View = findViewById(R.id.contBtn_splash_view)
        b.setOnClickListener {
            startActivity(Intent(this@SplashScreen,RegisterActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()

        val currentUser = Firebase.auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}