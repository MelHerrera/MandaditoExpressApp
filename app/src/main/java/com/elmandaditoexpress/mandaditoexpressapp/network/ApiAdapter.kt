package com.elmandaditoexpress.mandaditoexpressapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    private const val baseUrl = "http://192.168.68.112:53185/"
    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}