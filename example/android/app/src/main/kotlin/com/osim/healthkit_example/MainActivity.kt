package com.osim.healthkit_example

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import com.osim.healthkit.utils.BetterActivityResult
import io.flutter.embedding.android.FlutterFragmentActivity

class MainActivity: FlutterFragmentActivity() {
    private lateinit var _launcher: BetterActivityResult<Intent, ActivityResult>

    val launcher get() = _launcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _launcher = BetterActivityResult.registerActivityForResult(this)
    }
}
