package com.example.safeticketsappcompose.biometrics

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.example.safeticketsappcompose.models.DynamicBiometrics
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class SensorDataCollector private constructor(context: Context) : SensorEventListener {

    companion object {
        private const val VECTOR_CALCULATION_INTERVAL = 2 // Интервал в секундах для вычисления вектора
        private var isInited: Boolean = false
        private lateinit var instance: SensorDataCollector
        private val accelerometerValues = FloatArray(3)
        private val accelerometerValuesLast = FloatArray(3)
        private val magneticFieldValues = FloatArray(3)
        private val magneticFieldValuesLast = FloatArray(3)
        private var lightSensorValue = 0f
        private var lightSensorValueLast = 0f
        private var lastUpdateTime: Long = 0

        private var maxVectorLengthAccelerometer = Float.MIN_VALUE
        private var maxVectorLengthMagnetic = Float.MIN_VALUE

        private var minVectorLengthAccelerometer = Float.MAX_VALUE
        private var minVectorLengthMagnetic = Float.MAX_VALUE

        private var minLightSensor = Float.MAX_VALUE
        private var maxLightSensor = Float.MIN_VALUE

        fun initialize(context: Context) {
            instance = SensorDataCollector(context)
        }

        fun startListening() {
            val sensorManager = instance.sensorManager
            val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

            sensorManager.registerListener(instance, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager.registerListener(instance, magneticField, SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager.registerListener(instance, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        fun stopListening() {
            instance.sensorManager.unregisterListener(instance)
        }
        fun resetValues() {
            lastUpdateTime = 0
            minLightSensor = Float.MAX_VALUE
            maxLightSensor = Float.MIN_VALUE
            maxVectorLengthAccelerometer = Float.MIN_VALUE
            maxVectorLengthMagnetic = Float.MIN_VALUE
            minVectorLengthAccelerometer = Float.MAX_VALUE
            minVectorLengthMagnetic = Float.MAX_VALUE
            for (i in 0 until 3) {
                accelerometerValues[i] = 0f
                accelerometerValuesLast[i] = 0f
                magneticFieldValues[i] = 0f
                magneticFieldValuesLast[i] = 0f
            }
        }

        fun getData(): DynamicBiometrics {
            return DynamicBiometrics(
                minDeviceOffset = minVectorLengthMagnetic,
                maxDeviceOffset = maxVectorLengthMagnetic,
                minDeviceAcceleration = minVectorLengthAccelerometer,
                maxDeviceAcceleration = maxVectorLengthAccelerometer,
                minLight = minLightSensor,
                maxLight = maxLightSensor
            )
            resetValues()
        }
    }

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                accelerometerValues[0] = event.values[0]
                accelerometerValues[1] = event.values[1]
                accelerometerValues[2] = event.values[2]
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                magneticFieldValues[0] = event.values[0]
                magneticFieldValues[1] = event.values[1]
                magneticFieldValues[2] = event.values[2]
            }
            Sensor.TYPE_LIGHT -> {
                lightSensorValue = event.values[0]
            }
        }

        // Рассчитываем длину вектора каждые 2 секунды
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUpdateTime >= VECTOR_CALCULATION_INTERVAL * 1000) {
            if (!isInited){
                updateCurrentValues()
                isInited = true
            } else {
                calculateVectorLength()
                maxLightSensor = max(maxLightSensor, lightSensorValueLast)
                minLightSensor = min(minLightSensor, lightSensorValueLast)
                updateCurrentValues()
            }
            lastUpdateTime = currentTime
        }
    }
    private fun updateCurrentValues(){
        accelerometerValuesLast[0] = accelerometerValues[0]
        accelerometerValuesLast[1] = accelerometerValues[1]
        accelerometerValuesLast[2] = accelerometerValues[2]

        magneticFieldValuesLast[0] = magneticFieldValues[0]
        magneticFieldValuesLast[1] = magneticFieldValues[1]
        magneticFieldValuesLast[2] = magneticFieldValues[2]

        lightSensorValueLast = lightSensorValue
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Изменение точности (accuracy) датчиков
    }

    private fun calculateVectorLength() {
        val vectorLengthAccelerometer = calculateVectorLength(accelerometerValues, accelerometerValuesLast)
        val vectorLengthMagnetic = calculateVectorLength(magneticFieldValues, magneticFieldValuesLast)
        updateMinMaxVectorLength(vectorLengthAccelerometer, vectorLengthMagnetic)
        // Здесь вы можете использовать vectorLength по вашему усмотрению
    }

    private fun calculateVectorLength(values1: FloatArray, values2: FloatArray): Float {
        // Расчет длины вектора по значениям из датчиков
        val result = FloatArray(3)
        for (i in 0 until 3) {
            result[i] = values1[i] - values2[i]
        }
        return sqrt(result[0] * result[0] + result[1] * result[1] + result[2] * result[2])
    }

    private fun updateMinMaxVectorLength(vectorLengthAccelerometer: Float, vectorLengthMagnetic: Float) {
        // Обновление максимальной и минимальной длины вектора
        maxVectorLengthAccelerometer = max(maxVectorLengthAccelerometer, vectorLengthAccelerometer)
        minVectorLengthAccelerometer = min(minVectorLengthAccelerometer, vectorLengthAccelerometer)

        maxVectorLengthMagnetic = max(maxVectorLengthMagnetic, vectorLengthMagnetic)
        minVectorLengthMagnetic = min(minVectorLengthMagnetic, vectorLengthMagnetic)

        Log.d ("vectors", "${maxVectorLengthAccelerometer}, ${minVectorLengthAccelerometer}, ${maxVectorLengthMagnetic}, ${minVectorLengthMagnetic}, ${vectorLengthMagnetic}, ${vectorLengthAccelerometer} ${maxLightSensor} ${minLightSensor}")
    }
}

