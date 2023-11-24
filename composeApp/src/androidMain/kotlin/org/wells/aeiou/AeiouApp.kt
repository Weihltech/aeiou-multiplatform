package org.wells.aeiou

import android.app.Application
import org.wells.aeiou.screenadaptor.ScreenAdaptor

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/24
 */
class AeiouApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ScreenAdaptor.Builder()
            .uiScreen(1080, 1920, 440)
            .fullScreen(true)
            .build()
            .effect(this)
    }
}