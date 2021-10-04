package com.dicoding.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val publishedAt: String,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    var isFavorite: Boolean = false,
)