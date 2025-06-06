package dev.donmanuel.newsapp.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.newsapp.data.model.Article
import dev.donmanuel.newsapp.data.model.ErrorResponse
import dev.donmanuel.newsapp.data.model.NewsResponse
import dev.donmanuel.newsapp.data.repository.OnlineNewsRepository
import dev.donmanuel.newsapp.utils.Resource
import io.ktor.client.call.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val onlineNewsRepository: OnlineNewsRepository
) : ViewModel() {


    var searchQuery by mutableStateOf("")

    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow


    fun searchQueryNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = onlineNewsRepository.searchNews(query)
                if (httpResponse.status.value in 200..299) {
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                } else {
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }

}