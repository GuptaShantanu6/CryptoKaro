package com.example.cryptokaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cryptokaro.adapters.cryptoPriceAdapter
import com.example.cryptokaro.model.cryptoInfoFromAPI
import com.example.cryptokaro.singleton.CryptoPriceSingleton
import okhttp3.*

//https://api.coingecko.com/api/v3/simple/price?ids=binancecoin%2Cbitcoin%2Ccardano%2Cchainlink%2Cdai%2Cdogecoin%2Cethereum%2Chex%2Clitecoin%2Cpolkadot%2Cripple%2Csolana%2Cstellar%2Ctether%2Ctron%2Cuniswap%2Cvechain&vs_currencies=INR

class CryptoPriceActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var cryptoAdapter : cryptoPriceAdapter? = null
    private var cCrypto : MutableList<cryptoInfoFromAPI>? = null
    private lateinit var progressBar : ProgressBar

    private var x : String = ""

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

        val cryptoBackBtn : ImageView = findViewById(R.id.cryptoBackButton)
        cryptoBackBtn.setOnClickListener {
            this@CryptoPriceActivity.onBackPressed()
            finish()
        }

        progressBar.visibility = View.VISIBLE

        cryptoDisplayByVolleyAndSingleton(object : VolleyCallBack {
            override fun onSuccess() {
                super.onSuccess()

                progressBar.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                cryptoAdapter?.notifyDataSetChanged()

            }
        })

        val refreshBtn : ImageView = findViewById(R.id.cryptoRefreshBtn)
        refreshBtn.visibility = View.VISIBLE

        refreshBtn.setOnClickListener {
            recyclerView?.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            cryptoDisplayByVolleyAndSingleton(object : VolleyCallBack {
                override fun onSuccess() {
                    super.onSuccess()

                    progressBar.visibility = View.GONE
                    recyclerView?.visibility = View.VISIBLE
                    cryptoAdapter?.notifyDataSetChanged()

                }
            })

        }

    }

    interface VolleyCallBack {
        fun onSuccess(){

        }
    }

    private fun cryptoDisplayByVolleyAndSingleton(callBack: VolleyCallBack) {

        cCrypto?.clear()

        val urlCoinGecko = "https://api.coingecko.com/api/v3/simple/price?ids=binancecoin%2Cbitcoin%2Ccardano%2Cchainlink%2Cdai%2Cdogecoin%2Cethereum%2Chex%2Clitecoin%2Cpolkadot%2Cripple%2Csolana%2Cstellar%2Ctether%2Ctron%2Cuniswap%2Cvechain&vs_currencies=INR"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, urlCoinGecko, null,
            { response ->
                x = response.getJSONObject("binancecoin").getString("inr").toString() + " ₹"
                var newCrypto = cryptoInfoFromAPI("Binance",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("polkadot").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Polkadot",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("tron").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Tron",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("chainlink").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Chainlink",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("ethereum").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Ethereum",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("uniswap").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Uniswap",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("vechain").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Vechain",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("solana").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Solana",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("litecoin").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Litecoin",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("tether").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Tether",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("bitcoin").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Bitcoin",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("dogecoin").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Dogecoin",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("ripple").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Ripple",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("stellar").getString("inr").toString() + " ₹"
                 newCrypto = cryptoInfoFromAPI("Stellar",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("cardano").getString("inr").toString() + " ₹"
                newCrypto = cryptoInfoFromAPI("Cardano",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("dai").getString("inr").toString() + " ₹"
                newCrypto = cryptoInfoFromAPI("Dai",x)
                cCrypto?.add(newCrypto)

                x = response.getJSONObject("hex").getString("inr").toString() + " ₹"
                newCrypto = cryptoInfoFromAPI("Hex",x)
                cCrypto?.add(newCrypto)

                callBack.onSuccess()

            },
            {
                Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show()
            })

        // Add the request to the RequestQueue.
        CryptoPriceSingleton.getInstance(this,).addToRequestQueue(jsonObjectRequest)

    }

}