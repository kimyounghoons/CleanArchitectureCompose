package net.deali.navigator

import android.app.Activity
import android.content.res.Resources.NotFoundException

object ActivityClassManager {
    private val activityMap = HashMap<NavigatorKey, Class<out Activity>>()

    fun register(key: NavigatorKey, value: Class<out Activity>): ActivityClassManager {
        activityMap[key] = value
        return this
    }

    fun getActivityClass(key: NavigatorKey): Class<out Activity> {
        return activityMap[key] ?: throw NotFoundException("액티비티를 찾을 수 없습니다.")
    }
}