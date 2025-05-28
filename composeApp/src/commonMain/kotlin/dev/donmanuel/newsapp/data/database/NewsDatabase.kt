package dev.donmanuel.newsapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.donmanuel.newsapp.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}