package com.example.safeticketsappcompose.biometrics

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import java.lang.ref.WeakReference

object StaticDataController {

    private var contextReference: WeakReference<Context>? = null

    fun initialize(context: Context) {
        contextReference = WeakReference(context.applicationContext)
    }

    private fun getContext(): Context? {
        return contextReference?.get()
    }

    fun getBatteryCharge(): Int {
        val batteryStatus = getBatteryStatus()
        return batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    }

    fun getBatteryStatus(): Intent {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        return getContext()?.registerReceiver(null, filter)!!
    }

    fun isCharging(): Int {
        val batteryStatus = getBatteryStatus()
        return batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
    }

    fun getDataTransmissionStand(): Int {
        val telephonyManager = getContext()?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.phoneType
    }

    fun isSimCardPresent(): Int {
        val telephonyManager = getContext()?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.simState
    }
}
