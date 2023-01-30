package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import net.deali.nativecore.model.Movie

@Composable
fun VerticalItem(item: Movie) {
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
                modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                model = item.imageUrl,
                contentDescription = "movie",
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
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

@Preview
@Composable
fun ItemPreview() {
    VerticalItem(
        Movie(
            title = "영화제목",
            imageUrl = "",
            isAdult = false
        )
    )
}