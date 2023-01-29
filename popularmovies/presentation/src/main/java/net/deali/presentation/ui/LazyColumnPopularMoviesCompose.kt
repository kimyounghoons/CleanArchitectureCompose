package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import net.deali.nativecore.Movie
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiError

@Composable
fun LazyColumnPopularMoviesCompose(
    items: PopularMovieEntity,
    apiError: ApiError
) {
    if (apiError == ApiError.None) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .height(148.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(items.movies) { index, item ->
                VerticalItem(item)
            }
        }
    } else {
        val errorMsg = when (apiError) {
            is ApiError.HttpError -> {
                "에러"
            }
            ApiError.NetworkError -> {
                "네트워크에러"
            }
            ApiError.None -> {
                ""
            }
            ApiError.UnknownError -> {
                "알수 없는 에러"
            }
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMsg)
        }

    }
}

