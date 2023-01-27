package net.deali.cleanarchitecturecompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import net.deali.cleanarchitecturecompose.ui.MainCompose
import net.deali.core.BaseActivity
import net.deali.presentation.PopularMoviesActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                MainViewModel.Event.GoToMovieSearchEvent -> {

                }
                MainViewModel.Event.GoToPopularMoviesEvent -> {
                    PopularMoviesActivity.open(this)
                }
            }
        }
    }

    @Composable
    override fun ComposeContent() {
        MainCompose(list = vm.list, vm::onClick)
    }
}