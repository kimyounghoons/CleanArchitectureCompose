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
import net.deali.presentation.ui.LazyRowPopularMoviesCompose

@Composable
fun MainCompose(
    items: List<BaseEntity>,
    onMorePopularMoviesClick: () -> Unit,
    onMoreNowPlayingMoviesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CommonTitle(
            title = stringResource(id = R.string.popular_movie),
            onClick = onMorePopularMoviesClick
        )
        LazyRowPopularMoviesCompose(items = items)
        CommonTitle(
            title = stringResource(id = R.string.now_playing),
            onClick = onMoreNowPlayingMoviesClick
        )
    }
}