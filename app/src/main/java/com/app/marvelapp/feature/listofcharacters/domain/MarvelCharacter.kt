package com.app.marvelapp.feature.listofcharacters.domain

import com.app.marvelapp.feature.favorites.domain.Favorite

data class MarvelCharacter(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val resourceUrl: String = "",
    val isFavorite: Boolean = false
)

fun MarvelCharacter.toFavorite() =
    Favorite(
        id = id,
        name = name,
        description = description,
        resourceUrl = resourceUrl
    )

fun MarvelCharacter.markAsFavorite(setFavorite: Boolean) =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        resourceUrl = resourceUrl,
        isFavorite = setFavorite
    )