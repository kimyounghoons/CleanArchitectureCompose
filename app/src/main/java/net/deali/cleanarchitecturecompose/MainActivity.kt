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
import net.deali.detail.presentation.MovieDetailActivity
import net.deali.nowplaying.presentation.NowPlayingMoviesActivity
import net.deali.presentation.MovieSearchActivity
import net.deali.presentation.PopularMoviesActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        vm.onRefresh()
    }

    private fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                MainViewModel.Event.GoToPopularMoviesEvent -> {
                    PopularMoviesActivity.open(this)
                }
                MainViewModel.Event.GoToNowPlayingMoviesEvent -> {
                    NowPlayingMoviesActivity.open(this)
                }
                is MainViewModel.Event.GoToDetailEvent -> {
                    MovieDetailActivity.open(
                        activity = this,
                        movieId = event.movieId,
                        title = event.title
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
                        onGoToDetail = vm::goToDetail
                    )
                }
            },
        )
    }
}