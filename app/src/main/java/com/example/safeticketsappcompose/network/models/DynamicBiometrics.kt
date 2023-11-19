package com.example.safeticketsappcompose.network.models

import com.google.gson.annotations.SerializedName

data class DynamicBiometrics(
    @SerializedName("min_device_offs")
    val minDeviceOffset: Float,
    @SerializedName("max_device_offs")
    val maxDeviceOffset: Float,
    @SerializedName("min_dev_acceleration")
    val minDeviceAcceleration: Float,
    @SerializedName("max_dev_acceleration")
    val maxDeviceAcceleration: Float,
    @SerializedName("min_light")
    val minLight: Float,
    @SerializedName("max_light")
    val maxLight: Float
    )
