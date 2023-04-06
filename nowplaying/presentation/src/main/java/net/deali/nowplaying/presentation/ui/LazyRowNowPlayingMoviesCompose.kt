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
import com.google.accompanist.pager.*
import net.deali.core.ui.compose.CustomHorizontalPagerIndicator
import net.deali.core.ui.compose.IndicatorType
import net.deali.coredomain.ApiException
import net.deali.coredomain.entity.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LazyRowNowPlayingMoviesCompose(
    items: List<BaseEntity>, onRefreshClick: () -> Unit,
    onGoToDetail: (movieEntity: MovieEntity) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
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
        val pagerState = rememberPagerState(0)
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            state = pagerState,
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
                        is ApiException.HttpException -> "통신에러코드: ${exception.code}\n다시 시도 해주세요."
                        ApiException.NetworkException -> "네트워크 확인 후 다시 시도 해주세요."
                        ApiException.UnknownException -> "알 수 없는 오류가 발생했습니다.\n다시 시도 해주세요."
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

        CustomHorizontalPagerIndicator(
            modifier = Modifier.padding(top = 10.dp),
            pagerState = pagerState,
            indicatorType = IndicatorType.General
        )

    }
}

