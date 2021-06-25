package com.example.cryptokaro.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptokaro.R
import com.example.cryptokaro.model.cryptoInfoFromAPI
import com.google.firebase.storage.FirebaseStorage

class cryptoPriceAdapter (private var cContext : Context, private var isFragment : Boolean = false, private var cCrypto : List<cryptoInfoFromAPI>)
    : RecyclerView.Adapter<cryptoPriceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(cContext).inflate(R.layout.crypto_price_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = cCrypto[position]

        holder.crypto_name.text = crypto.cryptoName
        holder.crypto_price.text = crypto.cryptoPrice

        FirebaseStorage.getInstance().reference.child("Crypto Images").child(crypto.cryptoName+".png")
            .downloadUrl.addOnSuccessListener {
            val x = it.toString()
            Glide.with(holder.itemView.context).load(x).into(holder.crypto_image)
        }

        Log.d("crypto Name ",crypto.cryptoName)



    }

    override fun getItemCount(): Int {
        return cCrypto.size
    }

    class ViewHolder(@NonNull itemView : View) : RecyclerView.ViewHolder(itemView) {
        var crypto_name : TextView = itemView.findViewById(R.id.cryptoNameViewInLayout)
        var crypto_price : TextView = itemView.findViewById(R.id.cryptoPriceViewInLayout)
        var crypto_image : ImageView = itemView.findViewById(R.id.cryptoImage)
    }

}