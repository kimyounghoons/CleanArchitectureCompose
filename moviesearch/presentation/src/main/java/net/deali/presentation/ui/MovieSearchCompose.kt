package net.deali.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.deali.core.ui.compose.LazyColumnMoviesCompose
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.MovieEntity

@Composable
fun MovieSearchCompose(
    items: List<BaseEntity>,
    onSearch: (String) -> Unit,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
    onGoToDetail: (movieEntity: MovieEntity) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(onSearch = onSearch)
        LazyColumnMoviesCompose(
            items = items,
            onLoadMore = onLoadMore,
            onRefresh = onRefresh,
            onGoToDetail = onGoToDetail
        )
    }
}
