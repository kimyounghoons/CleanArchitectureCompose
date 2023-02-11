package net.deali.detail.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.SecondScaffold

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<DetailViewModel>() {
    override val vm: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.onRefresh()
    }

    @Composable
    override fun ComposeContent() {
        SecondScaffold(
            title = vm.movieTitle,
            onBackPressed = {
                finish()
            }
        ) {
            SwipeRefresh(
                modifier = Modifier.fillMaxSize(),
                state = rememberSwipeRefreshState(isRefreshing = false),
                onRefresh = vm::onRefresh
            ) {
            }
        }
    }

    companion object {
        fun open(
            activity: Activity,
            movieId: Int,
            title: String
        ) {
            Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra(DetailViewModel.KEY_MOVIE_ID, movieId)
                putExtra(DetailViewModel.KEY_MOVIE_TITLE, title)
            }.let {
                activity.startActivity(it)
            }
        }
    }
}