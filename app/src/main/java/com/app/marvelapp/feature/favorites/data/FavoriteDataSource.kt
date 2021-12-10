package com.app.marvelapp.feature.favorites.data

import com.app.marvelapp.feature.favorites.domain.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteDataSource {
    suspend fun insert(favorite: Favorite)
    suspend fun deleteById(favoriteId: Long)
    fun getFavourites(): Flow<List<Favorite>>
}