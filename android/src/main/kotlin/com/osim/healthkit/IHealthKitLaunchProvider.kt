package com.osim.healthkit

import android.content.Intent
import androidx.activity.result.ActivityResult
import com.osim.healthkit.utils.BetterActivityResult

interface IHealthKitLaunchProvider {
    val launcher: BetterActivityResult<Intent, ActivityResult>?
}