package dev.donmanuel.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.donmanuel.newsapp.data.model.Article
import dev.donmanuel.newsapp.theme.cardMinSize
import dev.donmanuel.newsapp.theme.mediumPadding
import dev.donmanuel.newsapp.presentation.navigation.Route
import dev.donmanuel.newsapp.utils.randomUUIDStr
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    rootNavController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articleList, key = {
            it.publishedAt + randomUUIDStr()
        }) { item ->
            ArticleItem(
                article = item,
                onClick = {
                    val articleStr = Json.encodeToString(item)
                    rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("article", articleStr)
                    }

                    rootNavController.navigate(Route.NewsDetail)
                })
        }
    }
}