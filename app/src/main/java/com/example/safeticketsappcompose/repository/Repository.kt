package com.example.safeticketsappcompose.repository

import com.example.safeticketsappcompose.models.JwtTokenResponse
import com.example.safeticketsappcompose.models.RegisterResponse

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
}