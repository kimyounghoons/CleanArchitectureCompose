package net.deali.navigator

import android.app.Activity
import android.os.Bundle

interface Navigator {
    fun startActivity(activity: Activity, key: NavigatorKey, bundle: Bundle?)
}