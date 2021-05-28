package com.example.cryptokaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val b : Button = findViewById(R.id.signOutBtn)
        b.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, SplashScreen::class.java))
        }

    }

    override fun onBackPressed() {
        Toast.makeText(this@MainActivity, "Please log out to go back", Toast.LENGTH_SHORT).show()
    }
}