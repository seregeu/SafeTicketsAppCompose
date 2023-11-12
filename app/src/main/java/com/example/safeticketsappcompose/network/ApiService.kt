package com.example.safeticketsappcompose.network

import com.example.safeticketsappcompose.network.models.JwtTokenResponse
import com.example.safeticketsappcompose.network.models.LoginData
import com.example.safeticketsappcompose.network.models.RegisterData
import com.example.safeticketsappcompose.network.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @POST("/api/token/auth")
    suspend fun loginUser(@Body loginData: LoginData) : Response<JwtTokenResponse>

    @POST("/api/account/register")
    suspend fun registerUser(@Body registerData: RegisterData) : Response<RegisterResponse>
}