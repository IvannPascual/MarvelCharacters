package com.app.marvelapp.feature.listofcharacters.data

import com.app.marvelapp.common.data.remote.CompleteApiError
import com.app.marvelapp.common.data.remote.MarvelApiErrors
import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter

interface CharactersDataSource {
    suspend fun getCharacters():MyResult<List<MarvelCharacter>, CompleteApiError<MarvelApiErrors>>
    suspend fun getCharacterDetail(characterId: Long): MyResult<MarvelCharacter, CompleteApiError<MarvelApiErrors>>
}