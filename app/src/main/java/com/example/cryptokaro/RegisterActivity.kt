package com.example.cryptokaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerTab : TabLayout
    private lateinit var registerViewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        registerTab = findViewById(R.id.register_tab)
        registerViewPager = findViewById(R.id.register_viewPager)

        registerTab.addTab(registerTab.newTab().setText("Log In"))
        registerTab.addTab(registerTab.newTab().setText("Sign Up"))

        registerTab.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = RegisterTabAdapter(this@RegisterActivity, supportFragmentManager, registerTab.tabCount)
        registerViewPager.adapter = adapter

        registerViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(registerTab))
        registerTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    registerViewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })



    }
}