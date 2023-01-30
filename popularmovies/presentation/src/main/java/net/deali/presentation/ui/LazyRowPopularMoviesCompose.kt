package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiResponse

@Composable
fun LazyRowPopularMoviesCompose(
    items: PopularMovieEntity,
    apiResponse: ApiResponse
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (apiResponse == ApiResponse.Success) {
            itemsIndexed(items.movies) { index, item ->
                HorizontalItem(item)
            }
        } else {
            val errorMsg = when (apiResponse) {
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
            item {
                Box(

                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMsg)
                }
            }
        }
    }
}

