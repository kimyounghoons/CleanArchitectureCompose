package net.deali.cleanarchitecturecompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import net.deali.cleanarchitecturecompose.ui.MainCompose
import net.deali.core.BaseActivity
import net.deali.core.ui.compose.MainScaffold
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiResponse
import net.deali.presentation.MovieSearchActivity
import net.deali.presentation.PopularMoviesActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        vm.fetchPopularMovies()
    }

    private fun initObserver() {
        vm.event.observe(this) { event ->
            when (event) {
                MainViewModel.Event.GoToPopularMoviesEvent -> {
                    PopularMoviesActivity.open(this)
                }
                MainViewModel.Event.GoToNowPlayingMoviesEvent ->{
                    Toast.makeText(this, "작업중입니다.",Toast.LENGTH_SHORT).show()
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
                val popularItems by vm.popularItems.observeAsState(PopularMovieEntity())
                val popularApiResponse by vm.apiResponse.observeAsState(ApiResponse.Success)
                MainCompose(
                    popularItems = popularItems,
                    popularApiResponse = popularApiResponse,
                    onMorePopularMoviesClick = vm::onMorePopularMoviesClick,
                    onMoreNowPlayingMoviesClick = vm::onMoreNowPlayingMoviesClick
                )
            },
        )
    }
}