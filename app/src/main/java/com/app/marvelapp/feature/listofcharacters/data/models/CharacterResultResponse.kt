package com.app.marvelapp.feature.listofcharacters.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResultResponse(
    @SerialName("code") val code: Int,
    @SerialName("status") val status: String,
    @SerialName("copyright") val copyright: String,
    @SerialName("attributionText") val attributionText: String,
    @SerialName("attributionHTML") val attributionHTML: String,
    @SerialName("data") val dataResponse: CharacterDataContainerResponse
)
