package net.deali.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.deali.coredomain.entity.BaseEntity


@Composable
fun MovieSearchKeywordCompose(
    items: List<BaseEntity>,
    onSearch: (String) -> Unit,
    onSearchClear: () -> Unit,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
    onBottomRefresh: () -> Unit,
    onGoToSearchResult: (keyword: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(
            onSearch = onSearch,
            onGoToSearchResult = onGoToSearchResult,
            onSearchClear = onSearchClear
        )
        LazyColumnSearchKeywordsCompose(
            items = items,
            onLoadMore = onLoadMore,
            onRefresh = onRefresh,
            onBottomRefresh = onBottomRefresh,
            onGoToSearchResult = onGoToSearchResult
        )
    }
}
