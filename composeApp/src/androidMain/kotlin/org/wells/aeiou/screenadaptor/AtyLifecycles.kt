package org.wells.aeiou.screenadaptor

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Des:
 *
 *
 * @author Weihl
 * Created 2022/2/19
 */
internal interface AtyLifecycles : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // nothing
    }

    override fun onActivityStarted(activity: Activity) {
        // nothing
    }

    override fun onActivityResumed(activity: Activity) {
        // nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // nothing
    }

    override fun onActivityStopped(activity: Activity) {
        // nothing
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // nothing
    }

    override fun onActivityDestroyed(activity: Activity) {
        // nothing
    }
}