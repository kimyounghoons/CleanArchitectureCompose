package net.deali.detail.presentation

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
import net.deali.coredomain.entity.ErrorEntity
import net.deali.detail.domain.entity.DetailEntity
import net.deali.detail.presentation.ui.CollapsingToolbarParallaxEffect

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<DetailViewModel>() {
    override val vm: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.onRefresh()
    }

    @Composable
    override fun ComposeContent() {
        SwipeRefresh(
            modifier = Modifier.fillMaxSize(),
            state = rememberSwipeRefreshState(isRefreshing = false),
            onRefresh = vm::onRefresh
        ) {
            val item by vm.item.observeAsState(DetailEntity())
            when (item) {
                is DetailEntity -> {
                    CollapsingToolbarParallaxEffect(
                        vm.movieTitle,
                        item as DetailEntity,
                        onBackPressed = ::finish
                    )
                }
                is ErrorEntity -> {
                }
            }
        }
    }
}