package com.app.marvelapp.feature.favorites.data

import com.app.marvelapp.common.data.local.FavoriteDao
import com.app.marvelapp.common.data.local.FavoriteEntity
import com.app.marvelapp.feature.favorites.domain.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class FavoriteDataSourceImpl(private val favoriteDao: FavoriteDao): FavoriteDataSource {
    override suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite.toData())
    }

    override suspend fun deleteById(favoriteId: Long) {
        favoriteDao.deleteById(favoriteId)
    }

    override fun getFavourites(): Flow<List<Favorite>> {
        return  favoriteDao.getFavourites().mapLatest {
            it.map { favoriteEntity ->  favoriteEntity.toDomain() }
        }
    }
}

fun Favorite.toData()= FavoriteEntity(
    favoriteId = id,
    name = name,
    description = description,
    url = resourceUrl
)

fun FavoriteEntity.toDomain()= Favorite(
    id = favoriteId,
    name = name,
    description = description,
     resourceUrl = url.orEmpty()
)