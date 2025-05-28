package dev.donmanuel.newsapp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.donmanuel.newsapp.data.database.NewsDatabase
import dev.donmanuel.newsapp.data.datastore.dataStorePreferences
import dev.donmanuel.newsapp.utils.AppPreferences
import dev.donmanuel.newsapp.utils.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }
    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
