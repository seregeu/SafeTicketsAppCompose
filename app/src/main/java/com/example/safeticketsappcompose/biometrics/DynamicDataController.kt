package com.example.safeticketsappcompose.biometrics

import android.content.Context
import android.util.Log
import com.example.safeticketsappcompose.network.models.CoordinatesData
import com.example.safeticketsappcompose.network.models.DynamicBiometrics

class DynamicDataController {

    fun initSensorsDataCollector(context: Context){
        SensorDataCollector.initialize(context)
    }

    fun startListening(){
        SensorDataCollector.startListening()
    }

    fun stopListening(){
        SensorDataCollector.stopListening()
    }

    fun getSensorsData(): DynamicBiometrics{
        val sensorsData = SensorDataCollector.getData()
        SensorDataCollector.resetValues()
        return sensorsData
    }

    fun collectDynamicData(hitX: Float, hitY: Float){
        val coordinatesData = CoordinatesData(hitX = hitX, hitY = hitY)
        val sensorsData = getSensorsData()
        Log.d("Dynamicdata:", "${coordinatesData} ${sensorsData}")
    }

}