package org.wells.aeiou.screenadaptor

import android.app.Activity
import androidx.core.view.WindowCompat

/**
 * @desc:
 * 关于屏幕适配，可能用到方法
 * @author: Weihl
 * @date: 2022/10/12
 */

fun Activity.lightStatusBars(toggle: Boolean = true) {
    WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = toggle
}