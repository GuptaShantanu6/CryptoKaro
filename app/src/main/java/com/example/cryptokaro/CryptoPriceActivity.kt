package com.example.cryptokaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.cryptokaro.adapters.cryptoPriceAdapter
import com.example.cryptokaro.api.cryptoAPI
import com.example.cryptokaro.model.cryptoInfoFromAPI
import okhttp3.*
import java.io.IOException

//https://api.coingecko.com/api/v3/simple/price?ids=binancecoin%2Cbitcoin%2Ccardano%2Cchainlink%2Cdai%2Cdogecoin%2Cethereum%2Chex%2Clitecoin%2Cpolkadot%2Cripple%2Csolana%2Cstellar%2Ctether%2Ctron%2Cuniswap%2Cvechain&vs_currencies=INR

class CryptoPriceActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var cryptoAdapter : cryptoPriceAdapter? = null
    private var cCrypto : MutableList<cryptoInfoFromAPI>? = null
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_price)

        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.appOrange)

        recyclerView = findViewById(R.id.cryptoRecyclerView)
        recyclerView?.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(baseContext)
        recyclerView!!.layoutManager = linearLayoutManager

        cCrypto = ArrayList()
        cryptoAdapter = baseContext?.let { cryptoPriceAdapter(it, false, cCrypto as ArrayList<cryptoInfoFromAPI>) }
        recyclerView?.adapter =cryptoAdapter

        progressBar = findViewById(R.id.cryptoPriceProgressBar)

//        recyclerView!!.visibility = View.GONE
//        progressBar.visibility = View.VISIBLE

        progressBar.visibility = View.GONE

        cryptoPriceDisplay(progressBar)

    }

    private fun cryptoPriceDisplay(progressBar: ProgressBar) {

//        progressBar.visibility = View.VISIBLE
//        recyclerView?.visibility = View.GONE

        cCrypto?.clear()

        val urlCoinGecko = "https://api.coingecko.com/api/v3/simple/price?ids=binancecoin%2Cbitcoin%2Ccardano%2Cchainlink%2Cdai%2Cdogecoin%2Cethereum%2Chex%2Clitecoin%2Cpolkadot%2Cripple%2Csolana%2Cstellar%2Ctether%2Ctron%2Cuniswap%2Cvechain&vs_currencies=INR"
        val client = OkHttpClient()
        val request = Request.Builder().url(urlCoinGecko).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    System.err.println("Response not successful")
                    Toast.makeText(this@CryptoPriceActivity, "Please try again later", Toast.LENGTH_SHORT).show()
                }

                val json = response.body!!.string()
                val myData = Klaxon().parse<cryptoAPI>(json)

                if (myData != null) {
                    val binanceCoinPrice = myData.binancecoin.inr.toString()
                    val newCrypto = cryptoInfoFromAPI("Binance", binanceCoinPrice)

                    Log.d("fetched price", binanceCoinPrice)
                    Log.d("newCrypto Status", newCrypto.cryptoPrice)

                    cCrypto?.add(newCrypto)

                    Log.d("current cCrypto size", cCrypto?.size.toString())

                    cryptoAdapter?.notifyDataSetChanged()

                }

            }

        })

//        Log.d("current cCrypto size", cCrypto?.size.toString())
//
//        cryptoAdapter?.notifyDataSetChanged()
//
//        progressBar.visibility = View.GONE
//        recyclerView?.visibility = View.VISIBLE
    }
}