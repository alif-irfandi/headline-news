package com.dicoding.headlinenews.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val headlineNews = newsUseCase.getAllNews().asLiveData()
}