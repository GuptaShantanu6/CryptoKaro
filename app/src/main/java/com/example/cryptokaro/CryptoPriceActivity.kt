package com.example.cryptokaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
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

    private var x : Int = -1

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

        cryptoPriceDisplay(progressBar)

    }

    private fun cryptoPriceDisplay(progressBar: ProgressBar) {

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

                    val binance = myData.binancecoin.inr.toString() + " ₹"
                    var newCrypto = cryptoInfoFromAPI("Binance", binance)
                    cCrypto?.add(newCrypto)

                    val bitcoin = myData.bitcoin.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Bitcoin", bitcoin)
                    cCrypto?.add(newCrypto)

                    val cardano = myData.cardano.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Cardano", cardano)
                    cCrypto?.add(newCrypto)

                    val chainlink = myData.chainlink.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Chainlink", chainlink)
                    cCrypto?.add(newCrypto)

                    val dai = myData.dai.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Dai", dai)
                    cCrypto?.add(newCrypto)

                    val doge = myData.dogecoin.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Dogecoin", doge)
                    cCrypto?.add(newCrypto)

                    val ether = myData.ethereum.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Ethereum", ether)
                    cCrypto?.add(newCrypto)

                    val hex = myData.hex.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Hex", hex)
                    cCrypto?.add(newCrypto)

                    val lite = myData.litecoin.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Litecoin", lite)
                    cCrypto?.add(newCrypto)

                    val polka = myData.polkadot.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Polkadot", polka)
                    cCrypto?.add(newCrypto)

                    val ripple = myData.ripple.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Ripple", ripple)
                    cCrypto?.add(newCrypto)

                    val solana = myData.solana.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Solana", solana)
                    cCrypto?.add(newCrypto)

                    val Stellar = myData.bitcoin.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Stellar", Stellar)
                    cCrypto?.add(newCrypto)

                    val tether = myData.tether.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Tether", tether)
                    cCrypto?.add(newCrypto)

                    val tron = myData.tron.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Tron", tron)
                    cCrypto?.add(newCrypto)

                    val uni = myData.uniswap.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Uniswap", uni)
                    cCrypto?.add(newCrypto)

                    val ve = myData.vechain.inr.toString() + " ₹"
                    newCrypto = cryptoInfoFromAPI("Vechain", ve)

                    Log.d("fetched price", binance)
                    Log.d("newCrypto Status", newCrypto.cryptoPrice)
                    Log.d("current cCrypto size", cCrypto?.size.toString())

                    x = cCrypto!!.size

                }

            }

        })

        client.dispatcher.executorService.shutdown()

        while (cCrypto?.size == 0) {
            //Do nothing and wait....
        }

        Thread.sleep(3000)

        progressBar.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE


    }

}