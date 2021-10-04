package com.dicoding.core.data.local.room

import androidx.room.*
import com.dicoding.core.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(data: List<NewsEntity>)

    @Query("SELECT * FROM `news`")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM `news` WHERE publishedAt = :publishedAt")
    fun getOneNews(publishedAt: String): Flow<NewsEntity?>

    @Update
    suspend fun update(newsEntity: NewsEntity)

    @Query("SELECT * FROM `news` WHERE isFavorite = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>
}