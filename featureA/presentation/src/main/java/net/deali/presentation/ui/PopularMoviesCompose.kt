package net.deali.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.deali.domain.model.Movie
import net.deali.domain.model.PopularMovies
import net.deali.nativecore.ApiError

@Composable
fun PopularMoviesCompose(items: PopularMovies, apiError: ApiError) {
    if (apiError == ApiError.None) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items.movies) { index, item ->
                Item(item)
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
        Text(
            modifier = Modifier.fillMaxSize(),
            text = errorMsg,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Item(item: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(item.title)
        if (item.isAdult) {
            Text("성인 영화")
        }
    }
}