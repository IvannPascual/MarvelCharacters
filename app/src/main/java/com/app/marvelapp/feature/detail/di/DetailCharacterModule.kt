package com.app.marvelapp.feature.detail.di

import com.app.marvelapp.feature.detail.domain.GetCharacterDetailUseCase
import com.app.marvelapp.feature.detail.ui.DetailCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailCharacterModule = module {
    viewModel { (detailCharacterId: Long) ->
        DetailCharacterViewModel(detailCharacterId, get())
    }
    factory {
        GetCharacterDetailUseCase(get())
    }
}