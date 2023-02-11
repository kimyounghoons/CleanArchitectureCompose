package net.deali.nowplaying.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import coil.compose.AsyncImage
import net.deali.coredomain.entity.MovieEntity

@Composable
fun HorizontalItem(item: MovieEntity, onGoToDetail: (movieEntity: MovieEntity) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .clickable(
                onClick = {
                    onGoToDetail(item)
                }
            )
    ) {
        val (imageRef, titleRef, overViewRef) = createRefs()

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(imageRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            model = item.backdropUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "movie",
        )
        Text(
            modifier = Modifier.constrainAs(titleRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(imageRef.bottom)
                bottom.linkTo(overViewRef.top)
                width = Dimension.fillToConstraints
            },
            text = item.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier.constrainAs(overViewRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                visibility =
                    if (item.overview.isNotEmpty()) Visibility.Visible else Visibility.Gone
            },
            text = item.overview,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun HorizontalItemPreview() {
    HorizontalItem(
        MovieEntity(
            title = "영화제목", imageUrl = "", isAdult = false
        ),
        onGoToDetail = {}
    )
}