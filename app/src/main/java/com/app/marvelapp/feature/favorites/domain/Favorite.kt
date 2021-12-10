package com.app.marvelapp.feature.favorites.domain

import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter

data class Favorite(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val resourceUrl: String = ""
)

fun Favorite.toMarvelCharacter(isFavorite: Boolean) =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        resourceUrl = resourceUrl,
        isFavorite = isFavorite
    )

