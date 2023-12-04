package org.wells.aeiou

import android.app.Application
import android.content.Context
import org.wells.aeiou.screenadaptor.ScreenAdaptor

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/24
 */
class AeiouApp : Application() {

    companion object {
        private lateinit var appContext: Context
        val context: Context
            get() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

        ScreenAdaptor.Builder()
            .uiScreen(1080, 1920, 440)
            .fullScreen(true)
            .build()
            .effect(this)
    }
}