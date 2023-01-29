package net.deali.core.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.deali.core.R

@Composable
fun CommonTitle(
    title: String,
    subTitle: String = stringResource(id = R.string.more_text),
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .alignByBaseline(),
            text = title,
            color = Color.White,
            fontSize = 20.sp
        )
        val modifier = Modifier
            .alignByBaseline()
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(enabled = onClick != null, onClick = onClick ?: {})
            .padding(8.dp)

        Text(
            modifier = modifier,
            text = subTitle,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}