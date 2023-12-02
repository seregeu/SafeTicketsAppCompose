package com.example.safeticketsappcompose.network.models

import com.google.gson.annotations.SerializedName

data class StaticBiometrics(
    @SerializedName("battery_charge")
    val batteryCharge: Float,
    @SerializedName("battery_status")
    val batteryStatus: Float,
    @SerializedName("data_trans_stand")
    val dataTransStandart: Float,
    @SerializedName("sim_presence")
    val simPresence: Float
)