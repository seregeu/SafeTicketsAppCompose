package com.example.safeticketsappcompose.network.interceptors

import com.example.safeticketsappcompose.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = TokenManager.getToken()

        // Добавляем токен в заголовок, если он есть
        if (token != null) {
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "JWT $token")
                .build()
            return chain.proceed(modifiedRequest)
        }

        return chain.proceed(originalRequest)
    }

    private fun isAuthRequest(url: String): Boolean {
        return url.contains("/auth") || url.contains("/register")
    }
}