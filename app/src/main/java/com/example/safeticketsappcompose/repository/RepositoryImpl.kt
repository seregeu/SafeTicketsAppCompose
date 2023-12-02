package com.example.safeticketsappcompose.repository

import android.util.Log
import com.example.safeticketsappcompose.network.NetworkClient
import com.example.safeticketsappcompose.models.JwtTokenResponse
import com.example.safeticketsappcompose.models.LoginData
import com.example.safeticketsappcompose.models.RegisterData
import com.example.safeticketsappcompose.models.RegisterResponse
import com.example.safeticketsappcompose.network.TokenManager
import com.example.safeticketsappcompose.network.models.CoordinatesData
import com.example.safeticketsappcompose.network.models.DynamicBiometrics
import com.example.safeticketsappcompose.network.models.StaticBiometrics
import retrofit2.HttpException

class RepositoryImpl : Repository {
    val service = NetworkClient.getClient("http://192.168.1.103:8000/")

    override suspend fun login(username: String, password: String): JwtTokenResponse {

        try {
            val response = service.loginUser(LoginData(username, password))
            if (response.isSuccessful) {
                Log.d("loginUser", "Successful: ${response.body()}")
                storeJwtToken(response.body()?.token ?: "")
                NetworkClient.isLoggedIn = true
                return response.body() ?: throw Exception("Empty response body")
            } else {
                Log.d("loginUser", "Error: ${response.code()}")
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            Log.d("loginUser", "Exception ${e.message}")
            throw e
        } catch (e: Throwable) {
            Log.d("loginUser", "Ooops: Something else went wrong")
            throw e
        }
    }

    override suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ): RegisterResponse {
        try {
            val response = service.registerUser(
                RegisterData(
                    userName,
                    firstName,
                    lastName,
                    email,
                    phone,
                    phone
                )
            )
            if (response.isSuccessful) {
                Log.d("loginUser", "Successful: ${response.body()}")
                return response.body() ?: throw Exception("Empty response body")
            } else {
                Log.d("loginUser", "Error: ${response.code()}")
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            Log.d("loginUser", "Exception ${e.message}")
            throw e
        } catch (e: Throwable) {
            Log.d("loginUser", "Ooops: Something else went wrong")
            throw e
        }
    }

    override suspend fun searchTickets() {
        try {
            val response = service.searchTickets()
        } catch (e: HttpException) {
        }
    }

    override suspend fun buyTicket() {
        try {
            val response = service.buyTickets()
        } catch (e: HttpException) {

        }
    }
    override suspend fun sendDynamicParams(dynamicBiometrics: DynamicBiometrics): RegisterResponse {
        try {
            val response = service.sendDynamicParams(
                dynamicBiometrics
            )
            if (response.isSuccessful) {
                Log.d("DynamicParams", "Successful: ${response.body()}")
                return response.body() ?: throw Exception("Empty response body")
            } else {
                Log.d("DynamicParams", "Error: ${response.code()}")
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            Log.d("DynamicParams", "Exception ${e.message}")
            throw e
        } catch (e: Throwable) {
            Log.d("DynamicParams", "Ooops: Something else went wrong")
            throw e
        }
    }

    override suspend fun sendCoordinates(coordinatesData: CoordinatesData): RegisterResponse {
        try {
            val response = service.sendCoordinates(
                coordinatesData
            )
            if (response.isSuccessful) {
                Log.d("CoordinatesData", "Successful: ${response.body()}")
                return response.body() ?: throw Exception("Empty response body")
            } else {
                Log.d("CoordinatesData", "Error: ${response.code()}")
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            Log.d("CoordinatesData", "Exception ${e.message}")
            throw e
        } catch (e: Throwable) {
            Log.d("CoordinatesData", "Ooops: Something else went wrong")
            throw e
        }
    }

    override suspend fun sendStaticParams(staticBiometrics: StaticBiometrics): RegisterResponse {
        try {
            val response = service.sendStaticParams(
                staticBiometrics
            )
            if (response.isSuccessful) {
                Log.d("StaticData", "Successful: ${response.body()}")
                return response.body() ?: throw Exception("Empty response body")
            } else {
                Log.d("StaticData", "Error: ${response.code()}")
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            Log.d("StaticData", "Exception ${e.message}")
            throw e
        } catch (e: Throwable) {
            Log.d("StaticData", "Ooops: Something else went wrong")
            throw e
        }
    }

    override fun storeJwtToken(jwtToken: String) {
        TokenManager.saveToken(jwtToken)
    }

    override fun getJwtToken(): String {
        return TokenManager.getToken() ?: ""
    }
}