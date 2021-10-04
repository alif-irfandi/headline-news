package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {

    override fun getAllNews(): Flow<Resource<List<News>>> = newsRepository.getAllNews()

    override fun getOneNews(publishedAt: String): Flow<News?> =
        newsRepository.getOneNews(publishedAt)

    override fun getFavoriteNews(): Flow<List<News>> =
        newsRepository.getFavoriteNews()

    override suspend fun setFavoriteNews(data: News, status: Boolean?) =
        newsRepository.setFavoriteNews(data, status)
}