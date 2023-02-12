package net.deali.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.deali.core.ui.theme.CleanArchitectureComposeTheme

abstract class BaseActivity<VM : BaseViewModel> : ComponentActivity() {

    abstract val vm: VM

    @Composable
    abstract fun ComposeContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        setContent {
            CleanArchitectureComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeContent()
                }
            }
        }
    }

    open fun initObserver() {
    }
}