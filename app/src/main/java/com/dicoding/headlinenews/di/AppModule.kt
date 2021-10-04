package com.dicoding.headlinenews.di

import com.dicoding.core.domain.usecase.NewsInteractor
import com.dicoding.core.domain.usecase.NewsUseCase
import com.dicoding.headlinenews.detail.DetailViewModel
import com.dicoding.headlinenews.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> {
        NewsInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}