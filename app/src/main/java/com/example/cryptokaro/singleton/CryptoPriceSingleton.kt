package com.example.cryptokaro.singleton

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class CryptoPriceSingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE : CryptoPriceSingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?:CryptoPriceSingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req : Request<T>) {
        requestQueue.add(req)
    }

}