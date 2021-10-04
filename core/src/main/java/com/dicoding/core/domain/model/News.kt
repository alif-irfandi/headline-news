package com.dicoding.core.domain.model

import java.io.Serializable

data class News(
    val publishedAt: String,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    var isFavorite: Boolean,
) : Serializable