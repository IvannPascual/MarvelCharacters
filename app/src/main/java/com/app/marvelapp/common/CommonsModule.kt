package com.app.marvelapp.common

import com.app.marvelapp.common.navigation.Navigator
import org.koin.dsl.module

val commonsModule = module {
    single { Navigator() }
}