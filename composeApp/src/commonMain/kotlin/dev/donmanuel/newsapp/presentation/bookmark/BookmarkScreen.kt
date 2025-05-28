package dev.donmanuel.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import dev.donmanuel.newsapp.presentation.common.ArticleListScreen
import dev.donmanuel.newsapp.presentation.common.EmptyContent
import dev.donmanuel.newsapp.presentation.common.ShimmerEffect
import dev.donmanuel.newsapp.presentation.navigation.Route
import dev.donmanuel.newsapp.utils.navigationItemsLists
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_browse
import news_kmp_app.composeapp.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    rootNavController: NavController, paddingValues: PaddingValues
) {
    val bookmarkViewModel = koinViewModel<BookmarkViewModel>()
    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()
    val originDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier.fillMaxSize().padding(
            start = paddingValues.calculateStartPadding(originDirection),
            end = paddingValues.calculateEndPadding(originDirection),
            bottom = paddingValues.calculateBottomPadding(),
        ),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(navigationItemsLists[2].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, actions = {
                IconButton(onClick = {
                    rootNavController.navigate(Route.SettingDetail)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                    )
                }
            })

        uiState.DisplayResult(onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_bookmarks),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            } else {
                ArticleListScreen(
                    articleList = articleList, rootNavController = rootNavController
                )
            }
        }, onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_browse, onRetryClick = {
                bookmarkViewModel.getArticles()
            })
        })
    }
}