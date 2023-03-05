package net.deali.core.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.deali.coredomain.ApiException
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.BottomErrorEntity
import net.deali.coredomain.entity.EmptyEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.coredomain.entity.LoadingEntity
import net.deali.coredomain.entity.MovieEntity

@Composable
fun LazyColumnMoviesCompose(
    modifier: Modifier = Modifier,
    items: List<BaseEntity>,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
    onBottomRefresh: () -> Unit,
    onGoToDetail: (movieEntity: MovieEntity) -> Unit
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
                    is MovieEntity -> {
                        VerticalItem(item, onGoToDetail)
                    }

                    is EmptyEntity -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(this@BoxWithConstraints.maxHeight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.message,
                                color = Color.White
                            )
                        }
                    }

                    is LoadingEntity -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(148.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = Color.White
                            )
                        }
                    }

                    is ErrorEntity -> {
                        val errorMsg = when (val exception = item.exception) {
                            is ApiException.HttpException -> "에러코드: ${exception.code} 입니다."
                            ApiException.NetworkException -> "인터넷 연결을 확인해주세요."
                            ApiException.UnknownException -> "알 수 없는 에러입니다."
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

                    is BottomErrorEntity -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(148.dp)
                                .clickable(onClick = onBottomRefresh),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "다시 시도",
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

