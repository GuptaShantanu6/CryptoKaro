package com.example.cryptokaro.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.cryptokaro.R
import com.example.cryptokaro.adapters.HomeFragmentAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragmentBottomNav : Fragment() {

    private lateinit var homeTab : TabLayout
    private lateinit var homeViewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_bottom_nav, container, false)

        homeTab = view.findViewById(R.id.homeFragment_TabLayout)
        homeViewPager = view.findViewById(R.id.homeFragment_ViewPager)

        homeTab.addTab(homeTab.newTab().setText("Home"))
        homeTab.addTab(homeTab.newTab().setText("Videos"))
        homeTab.addTab(homeTab.newTab().setText("Courses"))
        homeTab.addTab(homeTab.newTab().setText("Investing"))

        homeTab.tabGravity = TabLayout.GRAVITY_FILL
        homeTab.setBackgroundColor(resources.getColor(R.color.appGrey))

        val adapter = HomeFragmentAdapter(container?.context!!, childFragmentManager, homeTab.tabCount)
        homeViewPager.adapter = adapter

        homeViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(homeTab))
        homeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    homeViewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


        return view
    }

}