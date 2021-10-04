package com.dicoding.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDatabaseDao(): NewsDao
}