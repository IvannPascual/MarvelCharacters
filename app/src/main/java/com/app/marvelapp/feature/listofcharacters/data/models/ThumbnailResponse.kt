package com.app.marvelapp.feature.listofcharacters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailResponse(val path: String, val extension: String)
