package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.deali.nativecore.model.Movie

@Composable
fun HorizontalItem(item: Movie) {
    Column(
        modifier = Modifier
            .width(108.dp)
            .fillMaxHeight()
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .aspectRatio(3 / 4f),
            model = item.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "movie",
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = item.title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun HorizontalItemPreview() {
    HorizontalItem(
        Movie(
            title = "영화제목",
            imageUrl = "",
            isAdult = false
        )
    )
}