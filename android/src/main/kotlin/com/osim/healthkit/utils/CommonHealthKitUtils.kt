package com.osim.healthkit.utils

import android.app.Activity
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.NetworkUtils
import io.flutter.plugin.common.MethodChannel

val commonHealthKitUtils = CommonHealthKitUtils()

class CommonHealthKitUtils {

    fun getManufacturer(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.success(
            try {
                DeviceUtils.getManufacturer()
            } catch (ex: Exception) {
                ""
            }
        )
    }

    fun getIP(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.success(
            try {
                val wifi = params?.contains("wifi") ?: false
                if (wifi) {
                    NetworkUtils.getIpAddressByWifi()
                } else {
                    val ipv6 = params?.contains("ipv6") ?: false
                    NetworkUtils.getIPAddress(!ipv6)
                }
            } catch (ex: Exception) {
                ""
            }
        )
    }
}