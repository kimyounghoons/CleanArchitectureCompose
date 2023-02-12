package net.deali.cleanarchitecturecompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.deali.cleanarchitecturecompose.R
import net.deali.core.ui.compose.CommonTitle
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.MovieEntity
import net.deali.nowplaying.presentation.ui.LazyRowNowPlayingMoviesCompose
import net.deali.presentation.ui.LazyRowPopularMoviesCompose

@Composable
fun MainCompose(
    popularMovies: List<BaseEntity>,
    nowPlayingMovies: List<BaseEntity>,
    onPopularMoviesMoreClick: () -> Unit,
    onPopularMoviesRefreshClick: () -> Unit,
    onNowPlayingMoviesMoreClick: () -> Unit,
    onNowPlayingMoviesRefreshClick: () -> Unit,
    onGoToDetail: (movieEntity: MovieEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CommonTitle(
            title = stringResource(id = R.string.popular_movie),
            onClick = onPopularMoviesMoreClick
        )
        LazyRowPopularMoviesCompose(
            items = popularMovies,
            onRefreshClick = onPopularMoviesRefreshClick,
            onGoToDetail = onGoToDetail
        )
        CommonTitle(
            title = stringResource(id = R.string.now_playing),
            onClick = onNowPlayingMoviesMoreClick
        )
        LazyRowNowPlayingMoviesCompose(
            items = nowPlayingMovies,
            onRefreshClick = onNowPlayingMoviesRefreshClick,
            onGoToDetail = onGoToDetail
        )
    }
}