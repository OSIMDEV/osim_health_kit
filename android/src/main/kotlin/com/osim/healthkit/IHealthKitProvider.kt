package com.osim.healthkit

import android.app.Activity
import com.osim.healthkit.provider.HealthKitProviderType
import io.flutter.plugin.common.MethodChannel

interface IHealthKitProvider {

    val type : HealthKitProviderType get() = HealthKitProviderType.Unknown

    fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)

    fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)

    fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)

    fun loadData(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)

    fun getVendor(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)

    fun navToSettings(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?)
}