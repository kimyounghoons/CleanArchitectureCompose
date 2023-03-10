package net.deali.nowplaying.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import net.deali.coredomain.ApiException
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.EmptyEntity
import net.deali.coredomain.entity.ErrorEntity
import net.deali.coredomain.entity.LoadingEntity
import net.deali.coredomain.entity.MovieEntity
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LazyRowNowPlayingMoviesCompose(
    items: List<BaseEntity>, onRefreshClick: () -> Unit,
    onGoToDetail: (movieEntity: MovieEntity) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val existErrorEntity = items.any { it is ErrorEntity }

    val horizontalPadding = if (existErrorEntity) {
        0.dp
    } else {
        (screenWidth - 280.dp) / 2
    }

    val itemViewHeight = 210.dp
    val viewRatio = if (existErrorEntity) {
        (screenWidth.value / itemViewHeight.value)
    } else {
        4f / 3f
    }

    val itemModifier: PagerScope.(Int) -> Modifier = { page ->
        Modifier
            .graphicsLayer {
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                lerp(
                    start = 0.9f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                alpha = lerp(
                    start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(viewRatio)
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        count = items.size,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
    ) { index ->
        val item = items[index]

        BoxWithConstraints(modifier = itemModifier(index)) {
            if (item is MovieEntity) {
                HorizontalItem(item, onGoToDetail)
            } else if (item is EmptyEntity) {
                Box(
                    modifier = Modifier
                        .height(this@BoxWithConstraints.maxHeight)
                        .width(this@BoxWithConstraints.maxWidth),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = item.message)
                }
            } else if (item is ErrorEntity) {
                val errorMsg = when (val exception = item.exception) {
                    is ApiException.HttpException -> "??????????????????: ${exception.code}\n?????? ?????? ????????????."
                    ApiException.NetworkException -> "???????????? ?????? ??? ?????? ?????? ????????????."
                    ApiException.UnknownException -> "??? ??? ?????? ????????? ??????????????????.\n?????? ?????? ????????????."
                }
                Box(
                    modifier = Modifier
                        .height(this@BoxWithConstraints.maxHeight)
                        .width(this@BoxWithConstraints.maxWidth)
                        .clickable(onClick = onRefreshClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = errorMsg)
                }
            } else if (item is LoadingEntity) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = Color.White
                    )
                }
            }
        }
    }

}

