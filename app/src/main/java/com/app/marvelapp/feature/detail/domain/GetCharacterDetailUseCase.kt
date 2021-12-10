package com.app.marvelapp.feature.detail.domain

import com.app.marvelapp.base.domain.UseCaseSuspend
import com.app.marvelapp.common.data.remote.UserFriendlyError
import com.app.marvelapp.common.data.remote.toBasicUi
import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.common.result.mapError
import com.app.marvelapp.feature.listofcharacters.data.CharactersDataSource
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter

private typealias GetCharacterDetailUseCaseT =
    UseCaseSuspend<Long, MyResult<MarvelCharacter, UserFriendlyError>>

class GetCharacterDetailUseCase(private val charactersDataSource: CharactersDataSource) :
    GetCharacterDetailUseCaseT {
    override suspend fun invoke(characterId: Long) =
        charactersDataSource.getCharacterDetail(characterId).mapError {
            it.toBasicUi()
        }
}
