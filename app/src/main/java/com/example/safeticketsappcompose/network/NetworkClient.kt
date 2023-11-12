package com.example.safeticketsappcompose.network

import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private var retrofit: ApiService? = null
    //temp

    fun getClient(baseUrl: String): ApiService {
        if (retrofit == null) {
            retrofit = Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
        return retrofit!!
    }
}