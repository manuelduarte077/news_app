package dev.donmanuel.newsapp

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.newsapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}