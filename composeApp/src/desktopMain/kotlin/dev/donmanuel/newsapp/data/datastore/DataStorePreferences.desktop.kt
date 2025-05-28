package dev.donmanuel.newsapp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.donmanuel.newsapp.utils.dataStoreFileName

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}