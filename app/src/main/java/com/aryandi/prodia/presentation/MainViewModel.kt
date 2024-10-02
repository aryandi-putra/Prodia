package com.aryandi.prodia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState(isLoading = true))

    val uiState =
        _uiState
            .onStart { getNews() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = NewsUiState(isLoading = true),
            )

    fun getNews(isRefresh: Boolean = false) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isRefresh = isRefresh)
        getNewsUseCase()
            .catch { _uiState.value = _uiState.value.copy(error = true) }
            .onCompletion { _uiState.value = _uiState.value.copy(isRefresh = false) }
            .collect { _uiState.value = NewsUiState(articles = it) }
    }
}

data class NewsUiState(
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: Boolean = false,
)