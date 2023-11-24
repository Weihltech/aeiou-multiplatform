package org.wells.aeiou.screenadaptor

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log

/**
 * Des:
 * 屏幕适配
 * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 *
 * @author Weihl
 * Created 2022/2/19
 */
class ScreenAdaptor internal constructor(builder: Builder) {

    private val printLog: Boolean = builder.printLog
    val uiScreen: UiScreen = builder.uiScreen
    val isFullScreen: Boolean = builder.isFullScreen

    class Builder constructor() {
        internal var printLog: Boolean = true
        internal var uiScreen: UiScreen = UiScreen(1080, 1920, 440)
        internal var isFullScreen: Boolean = true

        // 沉浸式设计稿（即包含顶部状态栏、隐藏底部导航栏）
        fun fullScreen(full: Boolean): Builder {
            isFullScreen = full
            return this
        }

        fun printLog(open: Boolean): Builder {
            printLog = open
            return this
        }

        /**
         * 参考 Android Studio ,layout/xml design , phone pixel (1080, 1920, 420)
         * 注意：值是 UI-设计稿的既定设计尺寸
         */
        fun uiScreen(width: Int, height: Int, dpi: Int): Builder {
            uiScreen = UiScreen(width, height, dpi)
            return this
        }

        internal constructor(screenAdaptor: ScreenAdaptor) : this() {
            uiScreen = screenAdaptor.uiScreen
        }

        fun build(): ScreenAdaptor = ScreenAdaptor(this)
    }

    // fun newBuilder(): Builder = Builder(this)

    /**
     * run Main Application
     */
    fun effect(application: Application) {
        RealAdaptation(application, this)
    }

    @SuppressLint("LogNotTimber")
    internal fun log(txt: String) {
        if (printLog) Log.d("ScreenAdaptor", txt)
    }
}