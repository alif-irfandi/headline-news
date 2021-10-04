package com.dicoding.core.data.local

import com.dicoding.core.data.local.entity.NewsEntity
import com.dicoding.core.data.local.room.NewsDao

class LocalDataSource(private val newsDao: NewsDao) {

    fun getAllNews() = newsDao.getAllNews()

    fun getOneNews(publishedAt: String) = newsDao.getOneNews(publishedAt)

    fun getFavoriteNews() = newsDao.getFavoriteNews()

    suspend fun insertNews(data: List<NewsEntity>) = newsDao.insertNews(data)

    suspend fun setFavoriteNews(data: NewsEntity, status: Boolean? = null) {
        if (status != null)
            data.isFavorite = status
        else
            data.isFavorite = !data.isFavorite
        newsDao.update(data)
    }
}