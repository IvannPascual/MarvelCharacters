package com.app.marvelapp.feature.favorites.domain

import com.app.marvelapp.base.domain.UseCase
import com.app.marvelapp.feature.favorites.data.FavoriteDataSource
import kotlinx.coroutines.flow.Flow

typealias GetFavoritesUseCaseT = UseCase<Unit, Flow<List<Favorite>>>

class GetFavoritesUseCase(private val datasource: FavoriteDataSource) : GetFavoritesUseCaseT {
    override fun invoke(params: Unit): Flow<List<Favorite>> = datasource.getFavourites()
}

