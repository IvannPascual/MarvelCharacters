package com.app.marvelapp.common.navigation

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

open class Navigator {

    private val _directions = MutableSharedFlow<Route>(extraBufferCapacity = 1)
    val directions: SharedFlow<Route> = _directions

    open fun goTo(directions: NavDirections) {
        _directions.tryEmit(Route.Forward(directions))
    }

    open fun back() {
        _directions.tryEmit(Route.Back)
    }

    open fun exit() {
        _directions.tryEmit(Route.Exit)
    }
}
