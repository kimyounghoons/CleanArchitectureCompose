package net.deali.core.ui.compose

import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.Visibility

var ConstrainScope.isVisible: Boolean
    get() {
        return this.visibility == Visibility.Visible
    }
    set(value) {
        this.visibility = if (value) {
            Visibility.Visible
        } else {
            Visibility.Gone
        }
    }

var ConstrainScope.isInVisible: Boolean
    get() {
        return this.visibility == Visibility.Invisible
    }
    set(value) {
        this.visibility = if (value) {
            Visibility.Invisible
        } else {
            Visibility.Visible
        }
    }