package com.example.cryptokaro

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cryptokaro.register_fragments.LogInRegisterFragment
import com.example.cryptokaro.register_fragments.SignUpRegisterFragment

@Suppress("DEPRECATION")
internal class RegisterTabAdapter (
    var context: Context,
    fm : FragmentManager,
    private var totalTabs : Int
) :
        FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LogInRegisterFragment()
            }
            1 -> {
                SignUpRegisterFragment()
            }
            else -> getItem(position)
        }
    }

}