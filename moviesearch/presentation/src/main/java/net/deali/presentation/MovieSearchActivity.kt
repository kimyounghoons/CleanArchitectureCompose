package net.deali.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.SecondScaffold
import net.deali.navigator.Navigator
import net.deali.navigator.NavigatorKey
import net.deali.presentation.ui.MovieSearchCompose
import javax.inject.Inject

@AndroidEntryPoint
class MovieSearchActivity : BaseActivity<MovieSearchViewModel>() {
    override val vm: MovieSearchViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                is MovieSearchViewModel.GoToDetailEvent -> {
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
        SecondScaffold(
            title = "영화 검색",
            onBackPressed = {
                finish()
            }
        ) {
            val items by vm.items.observeAsState(listOf())

            MovieSearchCompose(
                items = items,
                onSearch = vm::onSearch,
                onLoadMore = vm::onLoadMore,
                onRefresh = vm::onRefresh,
                onGoToDetail = vm::onGoToDetail
            )
        }
    }

    companion object {
        fun open(activity: Activity) {
            Intent(activity, MovieSearchActivity::class.java).let {
                activity.startActivity(it)
            }
        }
    }
}