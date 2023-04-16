package net.deali.core.ui.compose


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlin.math.absoluteValue
import kotlin.math.sign

@ExperimentalPagerApi
@Composable
fun CustomHorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pageCount: Int = pagerState.pageCount,
    pageIndexMapping: (Int) -> Int = { it },
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
    indicatorType: IndicatorType = IndicatorType.WaterDrop,
    cornerRadius: CornerRadius = CornerRadius(10f, 10f)
) {
    if (pageCount < 2) {
        return
    }

    val indicatorWidthPx = LocalDensity.current.run { indicatorWidth.roundToPx().toFloat() }
    val indicatorHeightPx = LocalDensity.current.run { indicatorHeight.roundToPx().toFloat() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(pageCount) {
                Box(indicatorModifier)
            }
        }

        when (indicatorType) {
            IndicatorType.General -> {//HorizontalPagerIndicator 라이브러리와 동일한 Indicator
                Box(
                    Modifier
                        .offset {
                            val position = pageIndexMapping(pagerState.currentPage)
                            val offset = pagerState.currentPageOffset
                            val next =
                                pageIndexMapping(pagerState.currentPage + offset.sign.toInt())
                            val scrollPosition =
                                ((next - position) * offset.absoluteValue + position)
                                    .coerceIn(
                                        0f, (pageCount - 1)
                                            .coerceAtLeast(0)
                                            .toFloat()
                                    )

                            IntOffset(
                                x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
                                y = 0
                            )
                        }
                        .size(width = indicatorWidth, height = indicatorHeight)
                        .background(
                            color = activeColor,
                            shape = indicatorShape,
                        )
                )
            }
            IndicatorType.WaterDrop -> {
                Canvas(
                    modifier = Modifier.height(indicatorHeight)
                ) {
                    val position = pageIndexMapping(pagerState.currentPage)
                    val offset = pagerState.currentPageOffset
                    val doubleOffset = offset * 2
                    val halfToEndDoubleOffset = (0.5f + offset) * 2
                    val next = pageIndexMapping(pagerState.currentPage + offset.sign.toInt())
                    val isScrollInProgress = position != next
                    val firstPage = 0
                    val lastPage = pageCount - 1
                    val zeroToHalfOfView = offset > 0f
                    val isFirstToLastPagigng =
                        ((next == firstPage || next == lastPage) && (position == firstPage || position == lastPage) && position != next)
                    val spacingPlusIndicatorWidthPx = spacingPx + indicatorWidthPx

                    if (isFirstToLastPagigng) {
                        if (zeroToHalfOfView) {
                            drawRoundRect(//마지막 아이템 고정으로 그리기
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(width = indicatorWidthPx, height = indicatorHeightPx),
                                topLeft = Offset(x = spacingPlusIndicatorWidthPx * position, y = 0f)
                            )

                            drawRoundRect(//첫번째 아이템 조금씩 커지게
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(
                                    width = indicatorWidthPx * doubleOffset,
                                    height = indicatorHeightPx
                                )
                            )
                        } else {
                            drawRoundRect(//첫번째 아이템 고정으로 그리기
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(width = indicatorWidthPx, height = indicatorHeightPx),
                                topLeft = Offset(x = spacingPlusIndicatorWidthPx * position, y = 0f)
                            )

                            val sizeWidth =
                                indicatorWidthPx - (indicatorWidthPx * halfToEndDoubleOffset)
                            drawRoundRect(//마지막 아이템 조금씩 작아지게
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(width = sizeWidth, height = indicatorHeightPx),
                                topLeft = Offset(
                                    x = (spacingPlusIndicatorWidthPx * lastPage) + (indicatorWidthPx * halfToEndDoubleOffset),
                                    y = 0f
                                )
                            )
                        }
                    } else if (isScrollInProgress) {//스크롤 시
                        if (zeroToHalfOfView) {
                            drawRoundRect(
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(
                                    width = indicatorWidthPx + (spacingPlusIndicatorWidthPx * doubleOffset),
                                    height = indicatorHeightPx
                                ),
                                topLeft = Offset(x = spacingPlusIndicatorWidthPx * position, y = 0f)
                            )
                        } else {
                            drawRoundRect(
                                color = activeColor,
                                cornerRadius = cornerRadius,
                                size = Size(
                                    width = (spacingPx + (indicatorWidthPx * 2)) - (spacingPlusIndicatorWidthPx * halfToEndDoubleOffset),
                                    height = indicatorHeightPx
                                ),
                                topLeft = Offset(
                                    x = spacingPlusIndicatorWidthPx * (next + halfToEndDoubleOffset),
                                    y = 0f
                                )
                            )
                        }
                    } else {//멈춰 있을 때
                        drawRoundRect(
                            color = activeColor,
                            cornerRadius = cornerRadius,
                            size = Size(width = indicatorWidthPx, height = indicatorHeightPx),
                            topLeft = Offset(x = spacingPlusIndicatorWidthPx * position, y = 0f)
                        )
                    }
                }
            }
        }

    }
}

sealed class IndicatorType {
    object General : IndicatorType()
    object WaterDrop : IndicatorType()
}