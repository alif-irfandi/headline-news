package com.dicoding.core.utils

import com.dicoding.core.data.local.entity.NewsEntity
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.core.domain.model.News

object DataMapper {

    fun mapResponsesToEntities(input: List<NewsResponse>) = input.map {
        NewsEntity(
            publishedAt = it.publishedAt,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
        )
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>) = input.map {
        News(
            publishedAt = it.publishedAt,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: News) = NewsEntity(
        publishedAt = input.publishedAt,
        title = input.title,
        url = input.url,
        urlToImage = input.urlToImage,
        isFavorite = input.isFavorite
    )


    fun mapEntityToDomain(input: NewsEntity?): News? {
        if (input == null) return null

        return News(
            publishedAt = input.publishedAt,
            title = input.title,
            url = input.url,
            urlToImage = input.urlToImage,
            isFavorite = input.isFavorite
        )
    }
}