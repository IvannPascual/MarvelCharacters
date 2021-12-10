package com.app.marvelapp.common.data.remote

import com.app.marvelapp.feature.listofcharacters.data.models.CharacterResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(@Query("limit") limit: Int): CharacterResultResponse

    @GET("/v1/public/characters/{characterId}")
    suspend fun getMarvelCharacterInfo(@Path("characterId") heroId: Long): CharacterResultResponse
}
