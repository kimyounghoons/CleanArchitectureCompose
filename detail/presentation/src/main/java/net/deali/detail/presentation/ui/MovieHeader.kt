package net.deali.detail.presentation.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import net.deali.detail.presentation.R


@OptIn(ExperimentalMotionApi::class)
@Composable
fun MovieDetailHeader(
    scrollState: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    backdropUrl: String,
    title: String
) {
    val context = LocalContext.current
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val progress by remember {
        derivedStateOf {
            maxOf(0f, minOf(scrollState.value / toolbarBottom, 1f))
        }
    }

    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val properties = motionProperties(id = "bgImage")

        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .layoutId("bgImage"),
            model = backdropUrl,
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.layoutId("title"),
            color = properties.value.color("background")
        )
    }
}