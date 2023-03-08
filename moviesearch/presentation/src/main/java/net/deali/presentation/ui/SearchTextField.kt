package net.deali.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.deali.moviesearch.presentation.R

@Composable
fun SearchTextField(
    onSearch: (String) -> Unit,
    onGoToSearchResult: (String) -> Unit,
    onSearchClear: () -> Unit
) {
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
        leadingIcon = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (inputText.isNotEmpty()) {
                IconButton(
                    onClick = {
                        inputText = ""
                        onSearchClear.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onGoToSearchResult(inputText)
            }
        ),
        singleLine = true
    )

    LaunchedEffect(inputText) {
        if (inputText.isBlank()) return@LaunchedEffect
        delay(500)
        onSearch(inputText)
    }
}
