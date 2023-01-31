package net.deali.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.deali.nativecore.model.BaseModel

@Composable
fun MovieSearchCompose(
    items: List<BaseModel>,
    onSearch: (String) -> Unit,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(onSearch = onSearch)
        LazyColumnMoviesCompose(
            items = items,
            onLoadMore = onLoadMore,
            onRefresh = onRefresh
        )
    }
}
