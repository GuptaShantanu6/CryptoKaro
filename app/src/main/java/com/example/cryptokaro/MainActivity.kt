package com.example.cryptokaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cryptokaro.bottomNavFragments.HistoryFragmentBottomNav
import com.example.cryptokaro.bottomNavFragments.HomeFragmentBottomNav
import com.example.cryptokaro.bottomNavFragments.NotifFragmentBottomNav
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.appOrange)

        val homeFragment = HomeFragmentBottomNav()
        val notifFragment = NotifFragmentBottomNav()
        val historyFragment = HistoryFragmentBottomNav()

        setCurrentFragment(homeFragment)

        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNavBar)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeIcon -> setCurrentFragment(homeFragment)
                R.id.heartIcon -> setCurrentFragment(notifFragment)
                R.id.historyIcon -> setCurrentFragment(historyFragment)

            }
            true
        }

        val fab : FloatingActionButton = findViewById(R.id.fabBtn)
        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameHome, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this@MainActivity, "Please log out to go back", Toast.LENGTH_SHORT).show()
    }
}