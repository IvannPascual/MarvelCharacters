package com.app.marvelapp.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteEntity::class],
    version = 1, exportSchema = false
)


abstract class MarvelDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}
