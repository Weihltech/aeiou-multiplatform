package org.wells.aeiou.screenadaptor

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.core.view.WindowCompat

/**
 * Des:
 *
 *
 * @author Weihl
 * Created 2022/2/19
 */
internal class RealAdaptation(
    private val application: Application,
    private val adaptor: ScreenAdaptor
) {

    // 备份设备显示指标
    private val deviceDMetricsBackup = DisplayMetrics()

    private val deviceDMetrics by lazy {
        val dDMetrics = application.resources.displayMetrics
        deviceDMetricsBackup.setTo(dDMetrics)
        logDMetrics("Device", deviceDMetricsBackup)
        dDMetrics
    }

    private val targetDensity by lazy {
        adaptor.uiScreen.toDensity(deviceDMetrics.widthPixels)
    }

    private val targetDensityDpi by lazy {
        adaptor.uiScreen.toDensityDpi(targetDensity)
    }

    // scaleDensity / density  = targetScaleDensity / targetDensity
    private val targetScaleDensity: Float
        get() = adaptor.uiScreen.toScaleDensity(
            deviceDMetricsBackup.scaledDensity,
            deviceDMetricsBackup.density,
            targetDensity
        )

    private val targetDisplayMetrics: DisplayMetrics
        get() = DisplayMetrics().apply {
            density = targetDensity
            densityDpi = targetDensityDpi
            scaledDensity = targetScaleDensity
        }

    init {
        // 打印 ui screen
        printUiScreen()
        // 调整应用 Application DisplayMetrics
        adjustDeviceDMetrics()
        // 页面生命周期监听
        activityLifecyclesMonitor()
        // 字体变化监听
        fontChangeMonitor()
    }

    private fun printUiScreen() {
        adaptor.log(adaptor.uiScreen.toString())
    }

    private fun activityLifecyclesMonitor() {
        application.registerActivityLifecycleCallbacks(object : AtyLifecycles {
            override fun onActivityPreCreated(activity: Activity, bundle: Bundle?) {
                adaptationUiScreen(activity)
            }
        })
    }

    private fun adaptationUiScreen(activity: Activity) =
        with(activity.resources.displayMetrics) {
            if (activity is Adaptation) {
                activity.window?.let {

                    if (adaptor.isFullScreen) {
                        // 延伸到状态栏下
                        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            it.attributes.fitInsetsTypes = WindowInsets.Type.statusBars()
                        }
                    }

                    //完全全屏，延伸到状态栏，底部横条栏
                    WindowCompat.setDecorFitsSystemWindows(it, activity.fitsSystemWindows())

                    // 状态栏 字体颜色
                    WindowCompat.getInsetsController(it, it.decorView)
                        .isAppearanceLightStatusBars = activity.lightStatusBars()
                    // 状态栏背景色
                    it.statusBarColor = activity.statusBarColor()
                }

                // 自定义适配 UI 参数
                activity.onDisplayMetrics(
                    deviceDMetricsBackup,
                    targetDisplayMetrics
                ).let {
                    density = it.density
                    scaledDensity = it.scaledDensity
                    densityDpi = it.densityDpi
                }
            } else {
                density = targetDensity
                scaledDensity = targetScaleDensity
                densityDpi = targetDensityDpi
            }
            logDMetrics(activity.javaClass.simpleName, this)
        }

    //private fun fullUiScreen(activity: Activity) =
    //    with(activity) {
    //        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    //        // 全屏，将内容延伸到系统顶部状态栏下
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    //            window.attributes.fitInsetsTypes = WindowInsets.Type.statusBars()
    //            window.statusBarColor = Color.TRANSPARENT
    //            //window.setDecorFitsSystemWindows(false) 完全全屏，延伸到状态栏，底部横条栏
    //        } else {
    //            // FitsSystemWindows : false时，表示页面布局(内容区)扩展到状态栏
    //            WindowCompat.setDecorFitsSystemWindows(window, false)
    //            //window.decorView.systemUiVisibility =
    //            //    (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    //        }
    //        // WindowCompat.getInsetsController(window,window.decorView)?.hide(WindowInsets.Type.statusBars())
    //    }

    private fun fontChangeMonitor() {
        with(application) {
            // 监听; 修改字体， 系统 scaledDensity 产生变化；
            // 那么原来计算适配的 targetScaledDensity 也应该对应适配变化
            registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0f) {
                        // originDisplayMetrics 同步系统字体设置的放大因子
                        deviceDMetricsBackup.scaledDensity = resources.displayMetrics.scaledDensity
                        adaptor.log(
                            "fontScale change scaledDensity:" +
                                    "${deviceDMetricsBackup.scaledDensity}"
                        )
                    }
                }

                // nothing
                override fun onLowMemory() {}

            })
        }

    }

    private fun adjustDeviceDMetrics() {
        with(deviceDMetrics) {
            // 适配调整 application
            density = targetDensity
            scaledDensity = targetScaleDensity
            logDMetrics("Adaptation", this)
        }
    }

    private fun logDMetrics(from: String, displayMetrics: DisplayMetrics) =
        with(displayMetrics) {
            adaptor.log("$from@: ${toString()}")
        }


}