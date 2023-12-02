package com.example.safeticketsappcompose.network

import com.example.safeticketsappcompose.models.JwtTokenResponse
import com.example.safeticketsappcompose.models.LoginData
import com.example.safeticketsappcompose.models.RegisterData
import com.example.safeticketsappcompose.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @POST("/api/token/auth")
    suspend fun loginUser(@Body loginData: LoginData) : Response<JwtTokenResponse>

    @POST("/api/account/register")
    suspend fun registerUser(@Body registerData: RegisterData) : Response<RegisterResponse>

    @GET("api/search")
    suspend fun searchTickets() : Response<RegisterResponse>

    @POST("api/buy")
    suspend fun buyTickets() : Response<RegisterResponse>
}