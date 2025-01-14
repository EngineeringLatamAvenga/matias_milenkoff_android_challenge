package com.mtmilenkoff.locationapp.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Returns whether the screen should be shown in Landscape mode
 * TODO add Size condition for Ipads if necessary
 */
@Composable
fun isScreenLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}
