package com.example.safeticketsappcompose.network

import com.example.safeticketsappcompose.network.models.CoordinatesData
import com.example.safeticketsappcompose.network.models.DynamicBiometrics
import com.example.safeticketsappcompose.network.models.JwtTokenResponse
import com.example.safeticketsappcompose.network.models.LoginData
import com.example.safeticketsappcompose.network.models.RegisterData
import com.example.safeticketsappcompose.network.models.RegisterResponse
import com.example.safeticketsappcompose.network.models.StaticBiometrics
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @POST("/api/token/auth")
    suspend fun loginUser(@Body loginData: LoginData) : Response<JwtTokenResponse>

    @POST("/api/account/register")
    suspend fun registerUser(@Body registerData: RegisterData) : Response<RegisterResponse>

    @POST("/mob-api/coordinates")
    suspend fun sendCoordinates(@Body coordinatesData: CoordinatesData): Response<RegisterResponse>

    @POST("/mob-api/dynamic-params")
    suspend fun sendDynamicParams(@Body dynamicData: DynamicBiometrics): Response<RegisterResponse>
    @POST("/mob-api/static-params")
    suspend fun sendStaticParams(@Body staticData: StaticBiometrics): Response<RegisterResponse>
}