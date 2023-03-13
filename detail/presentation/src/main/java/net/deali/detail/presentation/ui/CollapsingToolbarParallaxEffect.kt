package net.deali.detail.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import net.deali.detail.domain.entity.DetailEntity

private val headerHeight = 250.dp
private val toolbarHeight = 56.dp

@Composable
fun CollapsingToolbarParallaxEffect(
    title: String,
    detailEntity: DetailEntity,
    onBackPressed: () -> Unit
) {
    val scroll: ScrollState = rememberScrollState()

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(
        Modifier.fillMaxSize()
    ) {
        MovieDetailHeader(
            scroll,
            headerHeightPx,
            toolbarHeightPx,
            detailEntity.backdropUrl,
            title
        )
        MovieDetailCompose(detailEntity, scroll, headerHeight)
        Toolbar(scroll, headerHeightPx, toolbarHeightPx, title, onBackPressed)
    }
}

@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    title: String,
    onBackPressed: () -> Unit
) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(Color.Black),
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            title = { Text(title) },
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}