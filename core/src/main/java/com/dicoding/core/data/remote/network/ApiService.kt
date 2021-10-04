package com.dicoding.core.data.remote.network

import com.dicoding.core.data.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
    ): ListNewsResponse
}
