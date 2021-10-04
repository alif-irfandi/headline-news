package com.dicoding.core.data.remote

import android.util.Log
import com.dicoding.core.BuildConfig
import com.dicoding.core.data.remote.network.ApiResponse
import com.dicoding.core.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getHeadlineNews() = flow {
        try {
            val data = apiService.getNews(
                apiKey = BuildConfig.API_KEY,
                country = "id"
            ).news
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            val msg = e.toString()
            emit(ApiResponse.Error(msg))
            Log.e("RemoteDataSource", msg)
        }
    }.flowOn(Dispatchers.IO)
}