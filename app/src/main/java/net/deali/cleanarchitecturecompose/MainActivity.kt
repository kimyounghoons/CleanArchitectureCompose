package net.deali.cleanarchitecturecompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import net.deali.cleanarchitecturecompose.ui.MainCompose
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.MainScaffold
import net.deali.navigator.Navigator
import net.deali.navigator.NavigatorKey
import net.deali.nowplaying.presentation.NowPlayingMoviesActivity
import net.deali.presentation.MovieSearchActivity
import net.deali.presentation.PopularMoviesActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val vm: MainViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.onRefresh()
    }

    override fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                MainViewModel.GoToPopularMoviesEvent -> {
                    PopularMoviesActivity.open(this)
                }
                MainViewModel.GoToNowPlayingMoviesEvent -> {
                    NowPlayingMoviesActivity.open(this)
                }
                is MainViewModel.GoToDetailEvent -> {
                    navigator.startActivity(
                        this,
                        NavigatorKey.MovieDetail,
                        Bundle().apply {
                            putInt(NavigatorKey.MovieDetail.KEY_MOVIE_ID, event.movieId)
                            putString(NavigatorKey.MovieDetail.KEY_MOVIE_TITLE, event.title)
                        }
                    )
                }
            }
        }
    }

    @Composable
    override fun ComposeContent() {
        MainScaffold(
            title = "The Movie Database",
            actions = {
                IconButton(onClick = {
                    MovieSearchActivity.open(this@MainActivity)
                }) {
                    Icon(imageVector = Icons.Default.Search, "Search")
                }
            },
            content = {
                SwipeRefresh(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberSwipeRefreshState(isRefreshing = false),
                    onRefresh = vm::onRefresh
                ) {
                    val popularItems by vm.popularItems.observeAsState(listOf())
                    val nowPlayingItems by vm.nowPlayingItems.observeAsState(listOf())

                    MainCompose(
                        popularMovies = popularItems,
                        nowPlayingMovies = nowPlayingItems,
                        onPopularMoviesMoreClick = vm::onMorePopularMoviesClick,
                        onPopularMoviesRefreshClick = vm::onPopularMoviesRefreshClick,
                        onNowPlayingMoviesMoreClick = vm::onMoreNowPlayingMoviesClick,
                        onNowPlayingMoviesRefreshClick = vm::onNowPlayingMoviesRefreshClick,
                        onGoToDetail = vm::onGoToDetail
                    )
                }
            },
        )
    }
}