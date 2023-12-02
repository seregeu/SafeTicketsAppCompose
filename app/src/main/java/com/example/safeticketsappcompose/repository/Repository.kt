package com.example.safeticketsappcompose.repository

import com.example.safeticketsappcompose.models.JwtTokenResponse
import com.example.safeticketsappcompose.models.RegisterResponse
import com.example.safeticketsappcompose.network.models.CoordinatesData
import com.example.safeticketsappcompose.network.models.DynamicBiometrics
import com.example.safeticketsappcompose.network.models.StaticBiometrics

interface Repository {
    suspend fun login(username: String, password: String): JwtTokenResponse

    suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ): RegisterResponse

    suspend fun searchTickets()

    suspend fun buyTicket()
    suspend fun sendDynamicParams(dynamicBiometrics: DynamicBiometrics): RegisterResponse

    suspend fun sendCoordinates(coordinatesData: CoordinatesData): RegisterResponse

    suspend fun sendStaticParams(staticBiometrics: StaticBiometrics): RegisterResponse

    fun storeJwtToken(jwtToken: String)

    fun getJwtToken(): String
}