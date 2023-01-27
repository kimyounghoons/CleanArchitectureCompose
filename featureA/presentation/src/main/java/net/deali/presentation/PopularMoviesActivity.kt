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
import net.deali.domain.model.PopularMovies
import net.deali.nativecore.ApiError
import net.deali.presentation.ui.PopularMoviesCompose

@AndroidEntryPoint
class PopularMoviesActivity : BaseActivity<PopularMoviesViewModel>() {
    override val vm: PopularMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.fetch()
    }

    @Composable
    override fun ComposeContent() {
        val items by vm.items.observeAsState(PopularMovies())
        val apiError by vm.apiError.observeAsState(ApiError.None)
        PopularMoviesCompose(items, apiError)
    }

    companion object {
        fun open(activity: Activity) {
            Intent(activity, PopularMoviesActivity::class.java).let {
                activity.startActivity(it)
            }
        }
    }
}