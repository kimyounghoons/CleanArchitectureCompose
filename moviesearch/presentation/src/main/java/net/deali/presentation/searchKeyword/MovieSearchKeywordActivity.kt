package net.deali.presentation.searchKeyword

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
import net.deali.presentation.ui.MovieSearchKeywordCompose
import javax.inject.Inject

@AndroidEntryPoint
class MovieSearchKeywordActivity : BaseActivity<MovieSearchKeywordViewModel>() {
    override val vm: MovieSearchKeywordViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    @Composable
    override fun ComposeContent() {
        SecondScaffold(
            title = "영화 검색",
            onBackPressed = {
                finish()
            }
        ) {
            val items by vm.items.observeAsState(listOf())

            MovieSearchKeywordCompose(
                items = items,
                onSearch = vm::onSearch,
                onSearchClear = vm::onSearchClear,
                onLoadMore = vm::onLoadMore,
                onRefresh = vm::onRefresh,
                onBottomRefresh = vm::onBottomRefresh,
                onGoToSearchResult = vm::onGoToSearchResult
            )
        }
    }

    override fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                is MovieSearchKeywordViewModel.GoToSearchResultEvent -> {
                    navigator.startActivity(
                        this,
                        NavigatorKey.SearchResultMovies,
                        Bundle().apply {
                            putString(NavigatorKey.SearchResultMovies.KEY_KEYWORD, event.keyword)
                        }
                    )
                }
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            Intent(activity, MovieSearchKeywordActivity::class.java).let {
                activity.startActivity(it)
            }
        }
    }
}