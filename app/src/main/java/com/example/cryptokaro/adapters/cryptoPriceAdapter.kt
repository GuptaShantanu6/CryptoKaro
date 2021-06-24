package com.example.cryptokaro.adapters

import android.content.Context
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

class cryptoPriceAdapter (private var cContext : Context, private var isFragment : Boolean = false, private var cCrypto : List<cryptoInfoFromAPI>)
    : RecyclerView.Adapter<cryptoPriceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(cContext).inflate(R.layout.crypto_price_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = cCrypto[position]

        holder.crypto_name.text = crypto.cryptoName
        holder.crypto_price.text = crypto.cryptoPrice

        when (crypto.cryptoName) {
            "Binance" -> {
                holder.crypto_image.setBackgroundResource(R.drawable.ic_binance_logo)
            }
            "Bitcoin" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_bitcoin)
            }
            "Cardano" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_cardano)
            }
            "Chainlink" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_qvshhryc)
            }
            "Dai" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_dai_2)
            }
            "Dogecoin" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_iconfinder_2785490_blockchain_dogecoin_icon)
            }
            "Ethereum" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_ethereum_1)
            }
            "Hex" -> {
                holder.crypto_image.setImageResource(R.drawable.hex)
            }
            "Litecoin" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_litecoin)
            }
            "Polkadot" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_polkadot_new_dot_logo)
            }
            "Ripple" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_xrp_symbol_black)
            }
            "Solana" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_solana_sol_logo)
            }
            "Stellar" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_stellar_xlm_logo)
            }
            "Tether" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_tether_usdt_logo)
            }
            "Tron" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_tron_trx_logo)
            }
            "Uniswap" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_uniswap_uni_logo)
            }
            "Vechain" -> {
                holder.crypto_image.setImageResource(R.drawable.ic_vechain_vet_logo)
            }
        }

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