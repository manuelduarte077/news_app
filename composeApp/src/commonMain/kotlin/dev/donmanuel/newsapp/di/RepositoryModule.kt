package dev.donmanuel.newsapp.di

import dev.donmanuel.newsapp.data.database.NewsDatabase
import dev.donmanuel.newsapp.data.repository.LocalNewsRepository
import dev.donmanuel.newsapp.data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
    single {
        OnlineNewsRepository(get())
    }
}