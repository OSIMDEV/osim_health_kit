package com.osim.healthkit.model

import com.huawei.hihealthkit.data.HiHealthSessionData
import com.huawei.hihealthkit.data.type.HiHealthSessionType.*

val HiHealthSessionData.transformed get() = HuaWeiHealthKitSleepData(
    type = type,
    startDate = startTime.toDouble(),
    endDate = endTime.toDouble(),
    metaData = metaData ?: "",
    deviceModel = sourceDevice.deviceModel ?: "",
    deviceName = sourceDevice.deviceName ?: "",
    deviceType = sourceDevice.deviceType ?: "",
    deviceUniqueCode = sourceDevice.deviceUniqueCode ?: "",
    updateTime = updateTime.toDouble(),
)

enum class SleepDateType {
    WAKE,
    LIGHT,
    REM,
    DEEP,
    NOON,
    IN_BED,
    UNKNOWN,
}

val HuaWeiHealthKitSleepData.transformedType get() = when (type) {
    DATA_SESSION_CORE_SLEEP_WAKE -> SleepDateType.WAKE
    DATA_SESSION_CORE_SLEEP_SHALLOW -> SleepDateType.LIGHT
    DATA_SESSION_CORE_SLEEP_DREAM -> SleepDateType.REM
    DATA_SESSION_CORE_SLEEP_DEEP -> SleepDateType.DEEP
    DATA_SESSION_CORE_SLEEP_NOON -> SleepDateType.NOON
    DATA_SESSION_CORE_SLEEP_BED -> SleepDateType.IN_BED
    else -> SleepDateType.UNKNOWN
}