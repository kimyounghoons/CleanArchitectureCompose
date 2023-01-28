package net.deali.cleanarchitecturecompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainCompose(
    list: List<String>,
    onClick: (String) -> Unit
) {
    Column {
        list.forEach { title ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            onClick.invoke(title)
                        }
                    )
                    .padding(vertical = 16.dp),
                text = title,
                textAlign = TextAlign.Center
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
            )
        }
    }
}