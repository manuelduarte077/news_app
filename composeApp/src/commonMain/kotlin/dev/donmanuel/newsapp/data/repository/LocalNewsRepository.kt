package dev.donmanuel.newsapp.data.repository

import dev.donmanuel.newsapp.data.database.NewsDao
import dev.donmanuel.newsapp.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(
    private val newsDao: NewsDao
) {
    suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    suspend fun deleteAllBookmark() {
        newsDao.deleteAllBookmark()
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String): Article? {
        return newsDao.getArticle(articleId = articleId)
    }
}