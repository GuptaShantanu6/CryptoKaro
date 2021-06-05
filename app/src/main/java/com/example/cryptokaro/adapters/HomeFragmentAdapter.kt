package com.example.cryptokaro.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cryptokaro.homeTabLayoutFragments.CoursesTab_for_tabForHomeActivity_Fragment
import com.example.cryptokaro.homeTabLayoutFragments.HomeTab_for_tabForHomeActivity_Fragment
import com.example.cryptokaro.homeTabLayoutFragments.InvestingTab_for_tabForHomeActivity_Fragment
import com.example.cryptokaro.homeTabLayoutFragments.VideosTab_for_tabForHomeActivity_Fragment

@Suppress("DEPRECATION")
internal class HomeFragmentAdapter (
    var context : Context,
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
                HomeTab_for_tabForHomeActivity_Fragment()
            }
            1 -> {
                VideosTab_for_tabForHomeActivity_Fragment()
            }
            2 -> {
                CoursesTab_for_tabForHomeActivity_Fragment()
            }
            3 -> {
                InvestingTab_for_tabForHomeActivity_Fragment()
            }
            else -> getItem(position)

        }
    }

}