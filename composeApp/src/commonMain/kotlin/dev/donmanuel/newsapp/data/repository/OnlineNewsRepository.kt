package dev.donmanuel.newsapp.data.repository

import dev.donmanuel.newsapp.BuildKonfig.API_KEY
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class OnlineNewsRepository(
    private val networkModule: HttpClient
) {

    suspend fun getNews(category: String): HttpResponse {

        return networkModule.get {
            url("everything")
            parameter("q", category)
            parameter("apiKey", API_KEY)
        }

    }

    suspend fun searchNews(searchQuery: String): HttpResponse {
        return networkModule.get {
            url("everything")
            parameter("q", searchQuery)
            parameter("apiKey", API_KEY)
        }
    }
}