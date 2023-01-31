package net.deali.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.deali.moviesearch.presentation.R

@Composable
fun SearchTextField(onSearch: (String) -> Unit) {
    var inputText by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = inputText,
        onValueChange = {
            inputText = it
        },
        placeholder = {
            Text(stringResource(id = R.string.insert_movie))
        },
        singleLine = true
    )

    LaunchedEffect(inputText) {
        if (inputText.isBlank()) return@LaunchedEffect
        delay(1000)
        onSearch(inputText)
    }
}
