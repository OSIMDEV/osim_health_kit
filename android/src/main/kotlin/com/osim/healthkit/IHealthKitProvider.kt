package com.osim.healthkit

import android.app.Activity
import com.osim.healthkit.provider.HealthKitProviderType
import io.flutter.plugin.common.MethodChannel

interface IHealthKitProvider {

    val type : HealthKitProviderType get() = HealthKitProviderType.Unknown

    fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>)

    fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>)

    fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) = false

    fun loadData(context: Activity?, cb: MethodChannel.Result?): Any? = null
}