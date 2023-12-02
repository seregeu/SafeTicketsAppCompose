package com.example.safeticketsappcompose.ui.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.safeticketsappcompose.models.JwtTokenResponse
import com.example.safeticketsappcompose.repository.Repository
import com.example.safeticketsappcompose.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository: Repository = RepositoryImpl()
    var username: String = ""
    var password: String = ""

    fun authenticateUser() {
        var jwtToken = JwtTokenResponse("")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                jwtToken = repository.login(username, password)
            } catch (e: Exception) {
                Log.d("NetworkProblem", e.message.toString())
            }
            Log.d("loginUser", "Token is: ${jwtToken.token}")
        }
    }
}