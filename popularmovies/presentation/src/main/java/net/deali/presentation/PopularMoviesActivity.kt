package net.deali.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.SecondScaffold
import net.deali.presentation.ui.LazyColumnPopularMoviesCompose

@AndroidEntryPoint
class PopularMoviesActivity : BaseActivity<PopularMoviesViewModel>() {
    override val vm: PopularMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.onRefresh()
    }

    @Composable
    override fun ComposeContent() {
        SecondScaffold(title = "인기영화", onBackPressed = {
            finish()
        }) {
            SwipeRefresh(
                modifier = Modifier.fillMaxSize(),
                state = rememberSwipeRefreshState(isRefreshing = false),
                onRefresh = vm::onRefresh
            ) {
                val items by vm.items.observeAsState(listOf())
                val onLoadMore = vm::onLoadMore
                LazyColumnPopularMoviesCompose(
                    items = items,
                    onLoadMore = onLoadMore,
                    onRefresh = vm::onRefresh
                )
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            Intent(activity, PopularMoviesActivity::class.java).let {
                activity.startActivity(it)
            }
        }
    }
}