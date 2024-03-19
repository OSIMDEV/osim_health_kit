package com.osim.healthkit.provider

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.blankj.utilcode.util.DeviceUtils
import com.huawei.hihealth.error.HiHealthError
import com.huawei.hihealthkit.HiHealthDataQuery
import com.huawei.hihealthkit.HiHealthDataQueryOption
import com.huawei.hihealthkit.data.HiHealthSessionData
import com.huawei.hihealthkit.data.store.HiHealthDataStore
import com.huawei.hihealthkit.data.type.HiHealthSessionType
import com.huawei.hms.hihealth.HuaweiHiHealth
import com.huawei.hms.hihealth.data.Scopes
import com.osim.healthkit.HealthKitBaseActivity
import io.flutter.plugin.common.MethodChannel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HuaWeiHealthKitProvider : BaseHealthKitProvider() {

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

    override fun requireAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        (context as? HealthKitBaseActivity)?.apply {
            val intent = HuaweiHiHealth.getSettingController(this).requestAuthorizationIntent(scopes, true)
            launcher.launch(intent) {
                var success = false
                try {
                    val result = HuaweiHiHealth.getSettingController(this).parseHealthKitAuthResultFromIntent(it.data)
                    success = result?.isSuccess ?: false
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                cb?.success(success)
            }
        } ?: cb?.success(false)
    }

    override fun testAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        testAuth(context) {
            cb?.success(it)
        }
    }

    private fun testAuth(context: Activity?, cb: (Boolean)->Unit) {
        context?.apply {
            HuaweiHiHealth.getSettingController(this)?.healthAppAuthorization
                ?.addOnFailureListener {
                    cb(false)
                }
                ?.addOnSuccessListener {
                    cb(it)
                } ?: cb(false)
        } ?: cb(false)
    }

    override fun cancelAuth(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        (context as? Context)?.apply {
            HuaweiHiHealth.getSettingController(this)?.healthAppAuthorization
                ?.addOnFailureListener {
                    cb?.success(false)
                }
                ?.addOnSuccessListener {
                    cb?.success(it)
                } ?: cb?.success(false)
        } ?: cb?.success(false)
    }

    override fun loadData(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        val retrievedData = mutableListOf<HiHealthSessionData>()
        testAuth(context) { authorized ->
            if (authorized) {
                val startTime = (params?.get("startTime") as? Long) ?: -1L
                val endTime = (params?.get("endTime") as? Long) ?: -1L
                val timeout = (params?.get("timeout") as? Int) ?: -1
                if (startTime >= 0L && endTime >= 0L && timeout >= 0L) {
                    val hiHealthDataQuery = HiHealthDataQuery(
                        HiHealthSessionType.DATA_SESSION_CORE_SLEEP,
                        startTime, endTime, HiHealthDataQueryOption(),)
                    try {
                        HiHealthDataStore.execQuery(context, hiHealthDataQuery, timeout) { resultCode, data ->
                            when {
                                resultCode == HiHealthError.SUCCESS && data is List<*> -> {
                                    if (data.isNotEmpty()) {
                                        retrievedData.addAll(data.map { e -> e as HiHealthSessionData })
                                    } else {
                                        Log.e(TAG, "Empty sleep data")
                                    }
                                }
                                // 参数错误
                                resultCode == HiHealthError.PARAM_INVALID ->
                                    Log.e(TAG, "Param invalid")
                                // 运动健康版本过低, 不支持此功能
                                resultCode == HiHealthError.ERR_HEALTH_VERSION_IS_NOT_SUPPORTED ->
                                    Log.e(TAG, "Health application need to be updated")
                                // HMS Core版本过低
                                resultCode == HiHealthError.ERR_HMS_UNAVAILABLE_VERSION ->
                                    Log.e(TAG, "HMS version is too early")
                                // 授权失效
                                resultCode == HiHealthError.ERR_PERMISSION_EXCEPTION ->
                                    Log.e(TAG, "Permission denied")
                                // 未同意运动健康隐私协议
                                resultCode == HiHealthError.ERR_PRIVACY_USER_DENIED ->
                                    Log.e(TAG, "Privacy user denied")
                                // 网络异常
                                resultCode == HiHealthError.ERR_NETWORK ->
                                    Log.e(TAG, "Network request failed")
                                // 测试权限的用户数量超过限制
                                resultCode == HiHealthError.ERR_BETA_SCOPE_EXCEPTION ->
                                    Log.e(TAG, "Beta scope permission denied")
                                // 其他错误，建议提示调用失败
                                else ->
                                    Log.e(TAG, "Other error, invoking method failed")
                            }
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
        }
        cb?.success(retrievedData)
    }

    override fun getVendor(context: Activity?, cb: MethodChannel.Result?, params: Map<*, *>?) {
        cb?.success(
            try {
                DeviceUtils.getManufacturer()
            } catch (ex: Exception) {
                ""
            }
        )
    }
}