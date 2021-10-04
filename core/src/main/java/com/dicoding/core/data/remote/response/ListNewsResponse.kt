package com.dicoding.core.data.remote.response

import com.squareup.moshi.Json

data class ListNewsResponse(
    @Json(name = "articles") val news: List<NewsResponse>,
)