package net.deali.nowplaying.presentation.contentProvider

import net.deali.navigator.ActivityClassManager
import net.deali.navigator.NavigatorContentProvider
import net.deali.navigator.NavigatorKey
import net.deali.nowplaying.presentation.NowPlayingMoviesActivity

class NowPlayingContentProvider : NavigatorContentProvider() {
    override fun registerActivities() {
        ActivityClassManager.register(
            NavigatorKey.NowPlayingMovies,
            NowPlayingMoviesActivity::class.java
        )
    }
}