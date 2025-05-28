@file:OptIn(ExperimentalForeignApi::class)

package dev.donmanuel.newsapp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.donmanuel.newsapp.utils.dataStoreFileName
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producePath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        })
}

