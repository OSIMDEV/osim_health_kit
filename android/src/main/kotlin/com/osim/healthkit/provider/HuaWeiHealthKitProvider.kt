package com.osim.healthkit.provider

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.blankj.utilcode.util.DeviceUtils
import com.huawei.hms.hihealth.HuaweiHiHealth
import com.huawei.hms.hihealth.data.Scopes
import com.osim.healthkit.HealthKitBaseActivity
import com.osim.healthkit.IHealthKitProvider
import io.flutter.plugin.common.MethodChannel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HuaWeiHealthKitProvider : IHealthKitProvider {

    companion object {
        private const val TAG = "HuaWeiHealthKitHelper"
        private const val DEBUG = false

        private const val APP_ID_KEY = "com.huawei.hms.client.appid"
        private const val URI_THIRD_PARTY_ACCOUNT_AUTH = "huaweischeme://healthapp/oauth/thirdPartyAccountAuth?app_id="

        private val scopes get() = arrayOf(
            Scopes.HEALTHKIT_SLEEP_READ,
            Scopes.HEALTHKIT_HISTORYDATA_OPEN_WEEK,
            Scopes.HEALTHKIT_HISTORYDATA_OPEN_MONTH,
            Scopes.HEALTHKIT_HISTORYDATA_OPEN_YEAR,
        )

        fun sha256(context: Context): String? {
            try {
                val pkgInfo = context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNATURES
                )
                val signatures = pkgInfo.signatures
                val cert = signatures[0].toByteArray()
                val md = MessageDigest.getInstance("SHA-256")
                md.update(cert)
                val fingerprint = md.digest()
                return fingerprint.toHexString()
            } catch (ex: PackageManager.NameNotFoundException) {
                Log.e(TAG, "Package name not found", ex)
            } catch (ex: NoSuchAlgorithmException) {
                Log.e(TAG, "SHA-256 algorithm not found", ex)
            }
            return null
        }

        private fun ByteArray.toHexString(): String {
            return joinToString(":") { "%02X".format(it) }
        }
    }

    override val type: HealthKitProviderType
        get() = HealthKitProviderType.HuaWei

    override fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) {
        (context as? HealthKitBaseActivity)?.apply {
            val intent = HuaweiHiHealth.getSettingController(this).requestAuthorizationIntent(scopes, true)
            launcher.launch(intent) {
                val result = HuaweiHiHealth.getSettingController(this).parseHealthKitAuthResultFromIntent(it.data)
                cb?.success(result?.isSuccess ?: false)
            }
        }
    }

    override fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) {

    }

    override fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<String, Any?>) = false

    override fun loadData(context: Activity?, cb: MethodChannel.Result?): Any? {
        return DeviceUtils.getManufacturer()
    }

    override fun getVendor(cb: MethodChannel.Result?) {
        cb?.success(DeviceUtils.getManufacturer())
    }
}