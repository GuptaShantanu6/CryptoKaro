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

        if (crypto.cryptoName == "Binance") {
//            holder.crypto_image.setBackgroundResource(R.drawable.ic_binance_logo)
            Glide.with(holder.itemView.context).load("https://cryptologos.cc/logos/binance-coin-bnb-logo.svg?v=010").into(holder.crypto_image)
        }
        else if (crypto.cryptoName == "Bitcoin") {
            holder.crypto_image.setImageResource(R.drawable.ic_bitcoin)
        }
        else if (crypto.cryptoName == "Cardano") {
            holder.crypto_image.setImageResource(R.drawable.ic_cardano)
        }
        else if (crypto.cryptoName == "Chainlink") {
            holder.crypto_image.setImageResource(R.drawable.ic_qvshhryc)
        }
        else if (crypto.cryptoName == "Dai") {
            holder.crypto_image.setImageResource(R.drawable.ic_dai_2)
        }
        else if (crypto.cryptoName == "Dogecoin") {
            holder.crypto_image.setImageResource(R.drawable.ic_iconfinder_2785490_blockchain_dogecoin_icon)
        }
        else if (crypto.cryptoName == "Ethereum") {
            holder.crypto_image.setImageResource(R.drawable.ic_ethereum_1)
        }
        else if (crypto.cryptoName == "Hex") {
            holder.crypto_image.setImageResource(R.drawable.hex)
        }
        else if (crypto.cryptoName == "Litecoin") {
            holder.crypto_image.setImageResource(R.drawable.ic_litecoin)
        }
        else if (crypto.cryptoName == "Polkadot") {
            holder.crypto_image.setImageResource(R.drawable.ic_polkadot_new_dot_logo)
        }
        else if (crypto.cryptoName == "Ripple") {
            holder.crypto_image.setImageResource(R.drawable.ic_xrp_symbol_black)
        }
        else if (crypto.cryptoName == "Solana") {
            holder.crypto_image.setImageResource(R.drawable.ic_solana_sol_logo)
        }
        else if (crypto.cryptoName == "Stellar") {
            holder.crypto_image.setImageResource(R.drawable.ic_stellar_xlm_logo)
        }
        else if (crypto.cryptoName == "Tether") {
//            holder.crypto_image.setImageResource(R.drawable.ic_tether_usdt_logo)
            Glide.with(holder.itemView.context).load("https://cryptologos.cc/logos/tether-usdt-logo.svg?v=010").into(holder.crypto_image)

        }
        else if (crypto.cryptoName == "Tron") {
            holder.crypto_image.setImageResource(R.drawable.ic_tron_trx_logo)
        }
        else if (crypto.cryptoName == "Uniswap") {
            holder.crypto_image.setImageResource(R.drawable.ic_uniswap_uni_logo)
        }
        else if (crypto.cryptoName == "Vechain") {
            holder.crypto_image.setImageResource(R.drawable.ic_vechain_vet_logo)
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