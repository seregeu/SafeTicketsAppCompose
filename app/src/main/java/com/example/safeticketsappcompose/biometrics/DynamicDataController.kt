package com.example.safeticketsappcompose.biometrics

import android.content.Context
import android.util.Log
import com.example.safeticketsappcompose.models.CoordinatesData
import com.example.safeticketsappcompose.models.DynamicBiometrics
import com.example.safeticketsappcompose.network.NetworkClient
import com.example.safeticketsappcompose.repository.Repository
import com.example.safeticketsappcompose.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DynamicDataController {
    private val repository: Repository = RepositoryImpl()


    fun initSensorsDataCollector(context: Context){
        SensorDataCollector.initialize(context)
    }

    fun startListening(){
        SensorDataCollector.startListening()
    }

    fun stopListening(){
        SensorDataCollector.stopListening()
    }

    fun getSensorsData(): DynamicBiometrics {
        val sensorsData = SensorDataCollector.getData()
        SensorDataCollector.resetValues()
        return sensorsData
    }

    fun collectDynamicData(hitX: Float, hitY: Float){
        val coordinatesData = CoordinatesData(hitX = hitX, hitY = hitY)
        val sensorsData = getSensorsData()
        Log.d("Dynamicdata:", "${coordinatesData} ${sensorsData}")
        sendDynamicData(coordinatesData, sensorsData)
    }

    fun sendDynamicData(coordinatesData: CoordinatesData, dynamicBiometrics: DynamicBiometrics){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkClient.isLoggedIn){
                    val dynamicData = repository.sendDynamicParams(dynamicBiometrics)
                    val coordinatesData = repository.sendCoordinates(coordinatesData)
                }
            } catch (e: Exception) {
                Log.d("NetworkProblem", e.message.toString())
            }
        }
    }

}