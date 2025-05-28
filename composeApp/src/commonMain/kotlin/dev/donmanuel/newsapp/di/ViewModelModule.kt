package dev.donmanuel.newsapp.di


import dev.donmanuel.newsapp.ui.article_detail.ArticleDetailViewModel
import dev.donmanuel.newsapp.ui.bookmark.BookmarkViewModel
import dev.donmanuel.newsapp.ui.headline.HeadlineViewModel
import dev.donmanuel.newsapp.ui.search.SearchViewModel
import dev.donmanuel.newsapp.ui.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}