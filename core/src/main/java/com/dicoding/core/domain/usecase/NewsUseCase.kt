package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getAllNews(): Flow<Resource<List<News>>>

    fun getOneNews(publishedAt: String): Flow<News?>

    fun getFavoriteNews(): Flow<List<News>>

    suspend fun setFavoriteNews(data: News, status: Boolean? = null)
}