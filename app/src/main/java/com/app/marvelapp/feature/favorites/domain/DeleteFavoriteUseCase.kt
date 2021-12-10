package com.app.marvelapp.feature.favorites.domain

import com.app.marvelapp.base.domain.UseCaseSuspend
import com.app.marvelapp.feature.favorites.data.FavoriteDataSource

typealias DeleteFavoriteUseCaseT =
    UseCaseSuspend<Long, Unit>

class DeleteFavoriteUseCase(private val favoriteDataSource: FavoriteDataSource) :
    DeleteFavoriteUseCaseT {
    override suspend fun invoke(params: Long) = favoriteDataSource.deleteById(params)
}

