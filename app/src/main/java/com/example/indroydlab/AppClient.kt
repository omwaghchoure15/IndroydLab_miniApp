package com.example.indroydlab

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

class ApiClient(private val context: Context) {
    val retrofit: Retrofit by lazy {
        val baseUrl = EnvironmentManager.getBaseUrl(context)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
