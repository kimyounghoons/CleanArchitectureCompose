package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.deali.coredomain.Movie
import net.deali.coredomain.exception.CustomHttpException
import net.deali.coredomain.exception.NetworkException
import net.deali.nativecore.model.BaseModel
import net.deali.nativecore.model.EmptyModel
import net.deali.nativecore.model.ErrorModel

@Composable
fun LazyRowPopularMoviesCompose(
    items: List<BaseModel>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        itemsIndexed(items) { index, item ->
            if (item is Movie) {
                HorizontalItem(item)
            } else if (item is EmptyModel) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.message)
                }
            } else if (item is ErrorModel) {
                val exception = item.exception
                val message = when (exception) {
                    is CustomHttpException -> {
                        "${exception.code} 에러"
                    }
                    is NetworkException -> {
                        "네트워크에러"
                    }
                    else -> {
                        "알수 없는 에러"
                    }
                }
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = message)
                }
            }
        }
    }
}

