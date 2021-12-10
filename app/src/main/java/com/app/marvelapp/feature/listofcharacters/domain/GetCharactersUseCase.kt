package com.app.marvelapp.feature.listofcharacters.domain

import com.app.marvelapp.base.domain.UseCaseSuspend
import com.app.marvelapp.common.data.remote.UserFriendlyError
import com.app.marvelapp.common.data.remote.toBasicUi
import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.common.result.mapError
import com.app.marvelapp.feature.listofcharacters.data.CharactersDataSource

typealias GetCharactersUseCaseT =
    UseCaseSuspend<Unit, MyResult<List<MarvelCharacter>, UserFriendlyError>>

class GetCharactersUseCase(private val charactersDataSource: CharactersDataSource) : GetCharactersUseCaseT {
    override suspend fun invoke(params: Unit) = charactersDataSource.getCharacters().mapError {
        it.toBasicUi()
    }
}