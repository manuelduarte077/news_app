package dev.donmanuel.newsapp.presentation.search

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
import dev.donmanuel.newsapp.theme.xSmallPadding
import dev.donmanuel.newsapp.presentation.common.ArticleListScreen
import dev.donmanuel.newsapp.presentation.common.EmptyContent
import dev.donmanuel.newsapp.presentation.common.ShimmerEffect
import dev.donmanuel.newsapp.presentation.navigation.Route
import dev.donmanuel.newsapp.presentation.search.components.SearchBar
import dev.donmanuel.newsapp.theme.getFontFamily
import dev.donmanuel.newsapp.utils.navigationItemsLists
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_browse
import news_kmp_app.composeapp.generated.resources.ic_network_error
import news_kmp_app.composeapp.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
) {
    val searchViewModel = koinViewModel<SearchViewModel>()
    val uiState by searchViewModel.newsStateFlow.collectAsState()
    val originDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = paddingValues.calculateStartPadding(originDirection),
                end = paddingValues.calculateEndPadding(originDirection),
                bottom = paddingValues.calculateBottomPadding()
            ),
        verticalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(navigationItemsLists[1].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = getFontFamily()
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        rootNavController.navigate(Route.SettingDetail)
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                    )
                }
            }
        )

        SearchBar(
            text = searchViewModel.searchQuery,
            onValueChange = { query ->
                searchViewModel.searchQuery = query
            },
            onSearch = { query ->
                if (query.trim().isNotEmpty()) {
                    searchViewModel.searchQueryNews(query.trim())
                }
            },
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false,
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.type_to_search),
                        icon = Res.drawable.ic_browse, onRetryClick = {
                            if (searchViewModel.searchQuery.trim().isNotEmpty()) {
                                searchViewModel.searchQueryNews(searchViewModel.searchQuery.trim())
                            }
                        })
                } else {
                    ArticleListScreen(
                        articleList = articleList,
                        rootNavController = rootNavController
                    )
                }
            },
            onError = {
                EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                    if (searchViewModel.searchQuery.trim().isNotEmpty()) {
                        searchViewModel.searchQueryNews(searchViewModel.searchQuery.trim())
                    }
                })
            }
        )
    }
}