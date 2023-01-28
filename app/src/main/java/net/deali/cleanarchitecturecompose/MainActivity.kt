package net.deali.cleanarchitecturecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import net.deali.cleanarchitecturecompose.ui.MainCompose
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.MainScaffold
import net.deali.presentation.MovieSearchActivity
//import net.deali.presentation.MovieSearchActivity
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
                MainViewModel.Event.GoToPopularMoviesEvent -> {
                    PopularMoviesActivity.open(this)
                }
            }
        }
    }

    @Composable
    override fun ComposeContent() {
        MainScaffold(
            title = "CleanArchitecureCompose",
            actions = {
                IconButton(onClick = {
                    MovieSearchActivity.open(this@MainActivity)
                }) {
                    Icon(imageVector = Icons.Default.Search, "Search")
                }
            },
            content = {
                MainCompose(
                    list = vm.list,
                    onClick = vm::onClick
                )
            },
        )
    }
}