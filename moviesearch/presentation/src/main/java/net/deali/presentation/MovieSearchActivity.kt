package net.deali.presentation

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import net.deali.core.BaseActivity

class MovieSearchActivity : BaseActivity<MovieSearchViewModel>() {
    override val vm: MovieSearchViewModel by viewModels()

    @Composable
    override fun ComposeContent() {

    }

    companion object {
        fun open(activity: Activity) {
            Intent(activity, MovieSearchActivity::class.java).let {
                activity.startActivity(it)
            }
        }
    }
}