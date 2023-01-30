package net.deali.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.deali.core.ui.compose.InfiniteListHandler
import net.deali.nativecore.ApiResponse
import net.deali.nativecore.model.BaseModel
import net.deali.nativecore.model.EmptyModel
import net.deali.nativecore.model.ErrorModel
import net.deali.nativecore.model.Movie

@Composable
fun LazyColumnPopularMoviesCompose(
    modifier: Modifier = Modifier,
    items: List<BaseModel>,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
) {
    val listState = rememberLazyListState()
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(items) { index, item ->
                when (item) {
                    is Movie -> {
                        VerticalItem(item)
                    }
                    is EmptyModel -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(this@BoxWithConstraints.maxHeight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "데이터가 없습니다.",
                                color = Color.White
                            )
                        }
                    }
                    is ErrorModel -> {
                        val errorMsg = when (item.apiResponse) {
                            is ApiResponse.HttpError -> {
                                "에러"
                            }
                            ApiResponse.NetworkError -> {
                                "네트워크에러"
                            }
                            ApiResponse.Success -> {
                                ""
                            }
                            ApiResponse.UnknownError -> {
                                "알수 없는 에러"
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(this@BoxWithConstraints.maxHeight)
                                .clickable(onClick = onRefresh),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = errorMsg,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
    InfiniteListHandler(listState = listState, onLoadMore = onLoadMore)
}

