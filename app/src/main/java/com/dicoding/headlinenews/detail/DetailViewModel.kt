package com.dicoding.headlinenews.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    fun getOneNews(publishedAt: String) = newsUseCase.getOneNews(publishedAt).asLiveData()

    fun setFavoriteNews(data: News, status: Boolean) {
        viewModelScope.launch {
            newsUseCase.setFavoriteNews(data, status)
        }
    }
}