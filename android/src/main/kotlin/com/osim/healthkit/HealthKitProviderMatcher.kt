package com.osim.healthkit

import com.osim.healthkit.provider.DefaultHealthKitProvider
import com.osim.healthkit.provider.HealthKitProviderType
import com.osim.healthkit.provider.HuaWeiHealthKitProvider
import com.osim.healthkit.provider.SamsungHealthKitProvider

class HealthKitProviderMatcher(
    private val table: MutableMap<HealthKitProviderType, IHealthKitProvider> = mutableMapOf()
) {
    init {
        HealthKitProviderType.values().forEach {
            table[it] = when (it) {
                HealthKitProviderType.HuaWei  -> HuaWeiHealthKitProvider()
                HealthKitProviderType.Samsung -> SamsungHealthKitProvider()
                else -> DefaultHealthKitProvider()
            }
        }
    }

    fun match(provider: String?) = lazy { table[HealthKitProviderType.match(provider)]!! }
}