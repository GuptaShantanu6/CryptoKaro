package com.example.cryptokaro.homeTabLayoutFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptokaro.R
import com.example.cryptokaro.adapters.HomeItemAdapterForHomeTabForHomeFragment
import com.example.cryptokaro.model.HomeTabModelForHomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeTab_for_tabForHomeActivity_Fragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var homeItemAdapter : HomeItemAdapterForHomeTabForHomeFragment? = null
    private var mHomeItem : MutableList<HomeTabModelForHomeActivity>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_tab_for_tab_for_home_activity_, container, false)

        recyclerView = view.findViewById(R.id.homeItemRecyclerView)
        recyclerView?.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.layoutManager = linearLayoutManager

        mHomeItem = ArrayList()
        homeItemAdapter = context?.let { HomeItemAdapterForHomeTabForHomeFragment(it, true, mHomeItem as ArrayList<HomeTabModelForHomeActivity>) }
        recyclerView!!.adapter = homeItemAdapter

        val fdb = FirebaseDatabase.getInstance().reference.child("homeTabElements")
        fdb.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mHomeItem?.clear()
                for (ss in snapshot.children) {
                    val p =ss.getValue(HomeTabModelForHomeActivity::class.java)
                    if (p != null) {
                        mHomeItem?.add(p)
                    }
                }

                homeItemAdapter?.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return view

    }

}