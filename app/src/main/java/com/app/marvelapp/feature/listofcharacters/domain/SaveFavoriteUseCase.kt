package com.app.marvelapp.feature.listofcharacters.domain

import com.app.marvelapp.base.domain.UseCaseSuspend
import com.app.marvelapp.feature.favorites.data.FavoriteDataSource
import com.app.marvelapp.feature.favorites.domain.Favorite

typealias SaveEditFavoriteUseCaseT = UseCaseSuspend<Favorite, Unit>

class SaveFavoriteUseCase(
    private val datasource: FavoriteDataSource,
) : SaveEditFavoriteUseCaseT {
    override suspend fun invoke(params: Favorite) {
        datasource.insert(favorite = params)
    }
}
