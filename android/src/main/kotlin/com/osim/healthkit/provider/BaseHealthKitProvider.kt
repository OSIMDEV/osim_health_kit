package com.osim.healthkit.provider

import android.app.Activity
import com.osim.healthkit.IHealthKitProvider
import io.flutter.plugin.common.MethodChannel

abstract class BaseHealthKitProvider : IHealthKitProvider {

    override val type: HealthKitProviderType
        get() = HealthKitProviderType.Unknown

    override fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.notImplemented()
    }

    override fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.notImplemented()
    }

    override fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.notImplemented()
    }

    override fun loadData(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.notImplemented()
    }

    override fun navToSettings(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.notImplemented()
    }
}