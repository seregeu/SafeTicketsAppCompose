package com.example.safeticketsappcompose.ui.Register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.safeticketsappcompose.network.models.RegisterResponse
import com.example.safeticketsappcompose.repository.Repository
import com.example.safeticketsappcompose.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val repository: Repository = RepositoryImpl()

    var username: String = ""
    var email: String = ""
    var password: String = ""
    var name: String = ""
    var lastname: String = ""
    var phone: String = ""

    fun registerUser() {
        var registerData = RegisterResponse("")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                registerData = repository.register(userName = username, email = email, password = password, firstName = name, lastName = lastname, phone = phone)
            } catch (e: Exception) {
                Log.d("NetworkProblem", e.message.toString())
            }
            Log.d("loginUser", "Token is: ${registerData}")
        }
    }
}