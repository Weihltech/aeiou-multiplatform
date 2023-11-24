package org.wells.aeiou.screenadaptor

import android.graphics.Color
import android.util.DisplayMetrics

/**
 * Des:
 * 自定义，当前 Activity ,以实现适配
 *
 * YouActivity : Adaptation{
 *  ...onDisplayMetrics...{
 *   // do something
 *   return youDisplayMetrics
 *  }
 * }
 *
 * @author Weihl
 * Created 2022/2/19
 */
interface Adaptation {

    /**
     * 处理当前 Activity / Window 适配界面；
     * 常规情况，在应用初始化时，已经确认 UI-设计尺寸；无须再页面中处理适配；
     * 此方法是预留入口，应用于：处理旧设计稿或其他尺寸不一致的问题，只会修改当前页面适配参数
     */
    fun onDisplayMetrics(
        device: DisplayMetrics,
        adaptor: DisplayMetrics
    ): DisplayMetrics = device

    // WindowCompat.getInsetsController(window,window.decorView).isAppearanceLightStatusBars = false
    fun lightStatusBars():Boolean = true

    fun statusBarColor():Int = Color.TRANSPARENT

    fun fitsSystemWindows():Boolean = true
}