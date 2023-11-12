package com.example.safeticketsappcompose.repository

import android.util.Log
import com.example.safeticketsappcompose.network.NetworkClient
import com.example.safeticketsappcompose.network.models.JwtTokenResponse
import com.example.safeticketsappcompose.network.models.LoginData
import com.example.safeticketsappcompose.network.models.RegisterData
import com.example.safeticketsappcompose.network.models.RegisterResponse
import retrofit2.HttpException

class RepositoryImpl : Repository {
    val service = NetworkClient.getClient("http://192.168.1.103:8000/")

    override suspend fun login(username: String, password: String): JwtTokenResponse {

        try {
            val response = service.loginUser(LoginData(username, password))
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
}