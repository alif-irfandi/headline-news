package com.dicoding.core.data.remote.response

data class NewsResponse(
    val publishedAt: String,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
)