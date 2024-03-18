package com.osim.healthkit.provider

enum class HealthKitProviderType(private val type: String) {
    HuaWei("huawei"),
    Samsung("samsung"),
    Unknown("unknown");

    companion object {
        fun match(type: String?) = values().find { type == it.type } ?: Unknown
    }
}