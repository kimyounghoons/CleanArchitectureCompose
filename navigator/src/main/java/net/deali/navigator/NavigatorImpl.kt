package net.deali.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class NavigatorImpl : Navigator {
    override fun startActivity(activity: Activity, key: NavigatorKey, bundle: Bundle?) {
        val intent = Intent(activity, ActivityClassManager.getActivityClass(key))
        bundle?.let { intent.putExtras(it) }
        activity.startActivity(intent)
    }
}