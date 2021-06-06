package com.example.cryptokaro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptokaro.R
import com.example.cryptokaro.model.HomeTabModelForHomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView

class HomeItemAdapterForHomeTabForHomeFragment (private var mContext : Context, private var isFragment : Boolean = true, private var mHomeItem : List<HomeTabModelForHomeActivity>)
    :RecyclerView.Adapter<HomeItemAdapterForHomeTabForHomeFragment.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_tab_item_home_fragment_layout, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeItem = mHomeItem[position]
        holder.itemText.text = homeItem.getText()

        val itemName = homeItem.getName()

        val fDb = FirebaseDatabase.getInstance().reference.child("homeTabElements")
        fDb.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Glide.with(mContext).load(snapshot.child(itemName).child("img").value).into(holder.itemImage)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun getItemCount(): Int {
        return mHomeItem.size
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage : CircleImageView = itemView.findViewById(R.id.itemImageView)
        val itemText : TextView = itemView.findViewById(R.id.itemText)

    }

}