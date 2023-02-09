package net.deali.core.ui.compose

import android.graphics.Movie
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import net.deali.coredomain.entity.MovieEntity

@Composable
fun VerticalItem(item: MovieEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(140.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(3 / 4f)
                    .clip(RoundedCornerShape(8.dp)),
                model = item.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "movie",
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold
                )
                Text(text = item.releaseDate)
                Text(
                    text = item.overview,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    VerticalItem(
        MovieEntity(
            title = "영화제목",
            imageUrl = "",
            isAdult = false,
            releaseDate = "2023-02-12",
            overview = "영화 설명 ~~~~",
        )
    )
}