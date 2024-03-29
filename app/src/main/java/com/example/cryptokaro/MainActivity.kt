package com.example.cryptokaro

import android.animation.Animator
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.cryptokaro.bottomNavFragments.HistoryFragmentBottomNav
import com.example.cryptokaro.bottomNavFragments.HomeFragmentBottomNav
import com.example.cryptokaro.bottomNavFragments.NotifFragmentBottomNav
import com.example.cryptokaro.fab_main_activity.CoursesFromFabMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.hypot
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    private lateinit var mRevealLayout : View
    private lateinit var mFab : FloatingActionButton

    private var isRevealed = false

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

        mRevealLayout = findViewById(R.id.RevealLayout)

        mFab = findViewById(R.id.fabBtn)
        mFab.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.appOrange,
                null
            )
        )

        mFab.setImageResource(R.drawable.ic_icons8_menu)

        mFab.setOnClickListener {
            revealLayoutFun()
        }

        val logOutBtn : View = findViewById(R.id.logOutBtnReveal)
        val cButton : View = findViewById(R.id.cButtonReveal)
        val pricesButton : View = findViewById(R.id.pricesButtonReveal)

        logOutBtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            finish()
        }

        cButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CoursesFromFabMainActivity::class.java))
        }

        pricesButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CryptoPriceActivity::class.java))
        }

    }

    private fun revealLayoutFun() {

        if (!isRevealed) {

            val x : Int = mRevealLayout.right
            val y : Int = mRevealLayout.top

            val startRadius = 0

            val endRadius = hypot(
                mRevealLayout.width.toDouble(),
                mRevealLayout.height.toDouble()
            ).toInt()

            mFab.animate()
                .rotationBy(180F)
                .setDuration(100)
                .scaleX(1.1f)
                .scaleY(1.1f)
                .withEndAction {
                    kotlin.run {

                        mFab.setImageResource(R.drawable.ic__close)

                        mFab.animate()
                            .rotationBy(180F)
                            .setDuration(100)
                            .scaleX(1F)
                            .scaleY(1F)
                            .start()

                    }
                }
                .start()

            val anim = ViewAnimationUtils.createCircularReveal(
                mRevealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            mRevealLayout.visibility = View.VISIBLE

            anim.start()

            isRevealed = true

        }
        else {

            val x : Int = mRevealLayout.right
            val y : Int = mRevealLayout.top

            val startRadius : Int = max(mRevealLayout.width, mRevealLayout.height)

            val endRadius = 0

            mFab.animate()
                .rotationBy(180F)
                .setDuration(100)
                .scaleX(1.1f)
                .scaleY(1.1f)
                .withEndAction {
                    kotlin.run {

                        mFab.setImageResource(R.drawable.ic_icons8_menu)

                        mFab.animate()
                            .rotationBy(180F)
                            .setDuration(70)
                            .scaleX(1F)
                            .scaleY(1F)
                            .start()

                    }
                }
                .start()

            val anim = ViewAnimationUtils.createCircularReveal(
                mRevealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            anim.addListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    mRevealLayout.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationRepeat(animation: Animator?) {}

            })

            anim.start()

            isRevealed = false

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