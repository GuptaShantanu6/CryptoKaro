package com.example.cryptokaro.fab_main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.example.cryptokaro.R

class CoursesFromFabMainActivity : AppCompatActivity() {

    private lateinit var backBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_from_fab_main)

        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.appOrange)

        backBtn = findViewById(R.id.cBackBtn)

        backBtn.setOnClickListener {

            this@CoursesFromFabMainActivity.onBackPressed()

        }

    }
}