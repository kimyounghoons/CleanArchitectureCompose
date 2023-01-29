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
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiError
import net.deali.presentation.ui.LazyColumnPopularMoviesCompose

@AndroidEntryPoint
class PopularMoviesActivity : BaseActivity<PopularMoviesViewModel>() {
    override val vm: PopularMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.fetch()
    }

    @Composable
    override fun ComposeContent() {
        SecondScaffold(title = "인기영화", onBackPressed = {
            finish()
        }) {
            val items by vm.items.observeAsState(PopularMovieEntity())
            val apiError by vm.apiError.observeAsState(ApiError.None)
            LazyColumnPopularMoviesCompose(items, apiError)
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