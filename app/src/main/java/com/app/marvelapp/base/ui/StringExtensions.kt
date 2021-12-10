package com.app.marvelapp.base.ui

fun String?.toApiMarvelUrl(extension: String): String {
    this?.let {
        val output = replace("http://", "https://")
        return "$output.$extension"
    } ?: return ""
}