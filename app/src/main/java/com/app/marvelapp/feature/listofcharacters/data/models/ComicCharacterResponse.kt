package com.app.marvelapp.feature.listofcharacters.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicCharacterResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("modified") val modified: String,
    @SerialName("resourceURI") val resourceUri: String,
    @SerialName("thumbnail") val thumbnailResponse: ThumbnailResponse
)