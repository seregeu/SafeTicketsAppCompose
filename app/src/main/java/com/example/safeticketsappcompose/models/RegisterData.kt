package com.example.safeticketsappcompose.models

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("username")
    val userName: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String
)
