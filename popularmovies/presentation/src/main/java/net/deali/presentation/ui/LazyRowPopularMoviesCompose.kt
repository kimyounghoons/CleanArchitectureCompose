package net.deali.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.deali.core.ui.uimodel.EmptyModel
import net.deali.core.ui.uimodel.ErrorModel
import net.deali.coredomain.Movie
import net.deali.nativecore.BaseModel
import net.deali.nativecore.exception.ApiException

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
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = item.message)
                }
            } else if (item is ErrorModel) {
                val errorMsg = when (val exception = item.exception) {
                    is ApiException.HttpException -> "통신에러코드: ${exception.code}\n다시 시도 해주세요."
                    ApiException.NetworkException -> "네트워크 확인 후 다시 시도 해주세요."
                    ApiException.UnknownException -> "알 수 없는 오류가 발생했습니다.\n다시 시도 해주세요."
                }
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = errorMsg)
                }
            }
        }
    }
}

