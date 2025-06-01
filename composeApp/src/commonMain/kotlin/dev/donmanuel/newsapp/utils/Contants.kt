package dev.donmanuel.newsapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.unit.Dp
import dev.donmanuel.newsapp.presentation.navigation.NavigationItem
import dev.donmanuel.newsapp.presentation.navigation.Route
import news_kmp_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

const val BASE_URL = "https://newsapi.org/v2/"
const val DB_Name = "myNewsDB"
const val dataStoreFileName = "setting.preferences_pb"
val categoryList = arrayListOf(
    "Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"
)
val navigationItemsLists = listOf(
    NavigationItem(
        icon = Res.drawable.ic_headline, title = Res.string.headlines, route = Route.Headline
    ),
    NavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = Route.Search,
    ),
    NavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark,
        route = Route.Bookmark,
    ),
)

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default), LIGHT_MODE(Res.string.light_mode), DARK_MODE(Res.string.dark_mode)
}

enum class Type {
    Mobile, Desktop
}

data class Size(
    val width: Dp, val height: Dp
)


val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) + scaleIn(
    initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)
)

val FadeOut = fadeOut(animationSpec = tween(90))