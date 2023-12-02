package com.example.safeticketsappcompose.network

import com.example.safeticketsappcompose.network.interceptors.AuthInterceptor
import com.example.safeticketsappcompose.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private var retrofit: ApiService? = null
    var isLoggedIn = false
    //temp

    fun getClient(baseUrl: String): ApiService {
        if (retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(AuthInterceptor())
                .build()

            retrofit = Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
        return retrofit!!
    }
}