package com.example.safeticketsappcompose.repository

import com.example.safeticketsappcompose.network.models.JwtTokenResponse
import com.example.safeticketsappcompose.network.models.RegisterResponse

interface Repository {
    suspend fun login(username: String, password: String): JwtTokenResponse

    suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ) : RegisterResponse
}