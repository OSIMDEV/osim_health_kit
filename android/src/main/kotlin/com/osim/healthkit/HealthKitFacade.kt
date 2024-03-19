package com.osim.healthkit

import android.app.Activity
import android.app.Application
import com.blankj.utilcode.util.Utils
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

@Suppress("UNUSED_PARAMETER")
object HealthKitFacade {
    const val TAG = "HealthKitFacade"
    const val DEBUG = false

    private val providerMatcher = HealthKitProviderMatcher()

    private var mounted = false

    fun mount(app: Application) {
        if (!mounted) {
            Utils.init(app)
            mounted = true
        }
    }

    fun unmount(app: Application) {
        if (mounted) {
            mounted = false
        }
    }

    fun handle(context: Activity?, call: MethodCall, result: MethodChannel.Result) {
        if (mounted) {
            try {
                val provider = providerMatcher.match(call.argument("provider")).value
                IHealthKitProvider::class.java.declaredMethods.find {
                    it.name == call.method
                }?.apply {
                    if (((call.arguments as? Map<*, *>)?.size ?: -1) > 1) {
                        invoke(provider, context, result, call.arguments)
                    } else {
                        invoke(provider, context, result, null)
                    }
                }
            } catch (ex: Exception) {
                result.notImplemented()
                ex.printStackTrace()
            }
        }
    }
}