package dev.donmanuel.newsapp.di


import dev.donmanuel.newsapp.presentation.article_detail.ArticleDetailViewModel
import dev.donmanuel.newsapp.presentation.bookmark.BookmarkViewModel
import dev.donmanuel.newsapp.presentation.headline.HeadlineViewModel
import dev.donmanuel.newsapp.presentation.search.SearchViewModel
import dev.donmanuel.newsapp.presentation.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}