package net.deali.core.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import net.deali.core.R

@Composable
fun MainScaffold(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                actions = actions,
                backgroundColor = colorResource(id = R.color.black)
            )
        }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            content()
        }
    }
}