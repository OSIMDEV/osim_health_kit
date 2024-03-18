package com.osim.healthkit.provider

import android.app.Activity
import com.blankj.utilcode.util.DeviceUtils
import com.osim.healthkit.IHealthKitProvider
import io.flutter.plugin.common.MethodChannel

class SamsungHealthKitProvider : IHealthKitProvider {

    override val type: HealthKitProviderType
        get() = HealthKitProviderType.Samsung

    override fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) {

    }

    override fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) {

    }

    override fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) = false

    override fun loadData(context: Activity?, cb: MethodChannel.Result?): Any? {
        return DeviceUtils.getManufacturer()
    }

    override fun getVendor(context: Activity?, cb: MethodChannel.Result?) {
        TODO("Not yet implemented")
    }
}