package net.deali.cleanarchitecturecompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.deali.cleanarchitecturecompose.R
import net.deali.core.ui.compose.CommonTitle
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiResponse
import net.deali.presentation.ui.LazyRowPopularMoviesCompose

@Composable
fun MainCompose(
    popularItems: PopularMovieEntity,
    popularApiResponse: ApiResponse,
    onMorePopularMoviesClick: () -> Unit,
    onMoreNowPlayingMoviesClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CommonTitle(
            title = stringResource(id = R.string.popular_movie),
            onClick = onMorePopularMoviesClick
        )
        LazyRowPopularMoviesCompose(items = popularItems, apiResponse = popularApiResponse)
        CommonTitle(
            title = stringResource(id = R.string.now_playing),
            onClick = onMoreNowPlayingMoviesClick
        )
    }
}