package com.dicoding.core.data

import com.dicoding.core.data.local.LocalDataSource
import com.dicoding.core.data.remote.RemoteDataSource
import com.dicoding.core.data.remote.network.ApiResponse
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.repository.INewsRepository
import com.dicoding.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> = localDataSource.getAllNews().map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getHeadlineNews()

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val mappedData = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(mappedData)
            }

        }.asFlow()

    override fun getOneNews(publishedAt: String): Flow<News?> = flow {
        val dataEntity = localDataSource.getOneNews(publishedAt)
        emit(
            DataMapper.mapEntityToDomain(
                dataEntity.firstOrNull()
            )
        )
    }

    override fun getFavoriteNews(): Flow<List<News>> =
        localDataSource.getFavoriteNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }


    override suspend fun setFavoriteNews(data: News, status: Boolean?) {
        val mappedData = DataMapper.mapDomainToEntity(data)
        localDataSource.setFavoriteNews(mappedData, status)
    }
}