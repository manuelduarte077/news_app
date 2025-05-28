package dev.donmanuel.newsapp.utils

import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import dev.donmanuel.newsapp.data.database.NewsDatabase

expect fun shareLink(url: String)

expect fun randomUUIDStr(): String

expect fun getType(): Type

@Composable
expect fun getScreenSize(): Size

expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>