package dev.donmanuel.newsapp.presentation.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.donmanuel.newsapp.data.model.Article
import dev.donmanuel.newsapp.presentation.article_detail.ArticleDetailScreen
import dev.donmanuel.newsapp.presentation.bookmark.BookmarkScreen
import dev.donmanuel.newsapp.presentation.headline.HeadlineScreen
import dev.donmanuel.newsapp.presentation.navigation.Route
import dev.donmanuel.newsapp.presentation.search.SearchScreen
import dev.donmanuel.newsapp.presentation.setting.SettingScreen
import dev.donmanuel.newsapp.presentation.setting.SettingViewModel
import kotlinx.serialization.json.Json

@Composable
fun NavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Headline,
    ) {
        composable<Route.Headline> {
            HeadlineScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Search> {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Bookmark> {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.NewsDetail> {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                ArticleDetailScreen(rootNavController, currentArticle)
            }
        }
        composable<Route.SettingDetail> {
            SettingScreen(navController = rootNavController, settingViewModel)
        }
    }
}