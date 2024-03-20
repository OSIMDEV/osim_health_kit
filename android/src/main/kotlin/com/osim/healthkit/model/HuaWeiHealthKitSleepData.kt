package com.osim.healthkit.model

data class HuaWeiHealthKitSleepData (
    val store: MutableMap<String, Any?> = mutableMapOf()
) {
    constructor(
        type: Int ,
        startDate: Double,
        endDate: Double,
        metaData: String,
        deviceModel: String,
        deviceName: String,
        deviceType: String,
        deviceUniqueCode: String,
        updateTime: Double,
    ) : this() {
        this.type = type
        this.startDate = startDate
        this.endDate = endDate
        this.metaData = metaData
        this.deviceModel = deviceModel
        this.deviceName = deviceName
        this.deviceType = deviceType
        this.deviceUniqueCode = deviceUniqueCode
        this.updateTime = updateTime
    }

    var type: Int by store

    var startDate: Double by store

    var endDate: Double by store

    var metaData: String by store

    var deviceModel: String by store

    var deviceName: String by store

    var deviceType: String by store

    var deviceUniqueCode: String by store

    var updateTime: Double by store
}