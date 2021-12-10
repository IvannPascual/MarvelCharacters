package com.app.marvelapp.common.navigation

import androidx.navigation.NavDirections

sealed class Route {
    open class Forward(val direction: NavDirections) : Route()
    object Back : Route()
    object Exit : Route()
}
