package dev.donmanuel.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.donmanuel.newsapp.theme.NewsAppTheme
import dev.donmanuel.newsapp.ui.MainScreen
import dev.donmanuel.newsapp.ui.setting.SettingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()
        NewsAppTheme(currentTheme) {
            MainScreen(settingViewModel)
        }
    }
}