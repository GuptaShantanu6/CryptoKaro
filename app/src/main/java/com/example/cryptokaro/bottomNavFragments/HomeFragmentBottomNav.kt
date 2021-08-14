package com.example.cryptokaro.bottomNavFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.cryptokaro.R
import com.example.cryptokaro.adapters.HomeFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HomeFragmentBottomNav : Fragment() {

    private lateinit var homeTab : TabLayout
    private lateinit var homeViewPager: ViewPager

    @SuppressLint("SetTextI18n")
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

        val UID = Firebase.auth.currentUser?.uid.toString()
        val userName : TextView = view.findViewById(R.id.userNameField)

//        var getName : String = ""
//        var finalName : String = ""
//
//        val fDb = FirebaseDatabase.getInstance().reference.child("Users")
//        fDb.addValueEventListener(object : ValueEventListener{
//            @SuppressLint("SetTextI18n")
//            override fun onDataChange(snapshot: DataSnapshot) {
//                userName.text = "Hey " + snapshot.child(UID).child("name").value.toString() + " \uD83D\uDE0A"
////                getName = snapshot.child(UID).child("name").value.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(requireContext(), "PLease restart the application", Toast.LENGTH_SHORT).show()
//            }
//
//        })

//        for (x in getName) {
//            if (x != ' ') {
//                finalName += x
//            }
//            else {
//                break
//            }
//        }

//        userName.text = finalName + "\uD83D\uDE0A"
//        userName.text = getName.toString()


        return view
    }

}