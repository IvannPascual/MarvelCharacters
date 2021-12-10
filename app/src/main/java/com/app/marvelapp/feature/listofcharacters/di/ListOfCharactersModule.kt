package com.app.marvelapp.feature.listofcharacters.di

import com.app.marvelapp.feature.listofcharacters.ui.ListOfCharactersViewModel
import com.app.marvelapp.feature.listofcharacters.data.CharacterDatasourceImpl
import com.app.marvelapp.feature.listofcharacters.data.CharactersDataSource
import com.app.marvelapp.feature.favorites.data.FavoriteDataSource
import com.app.marvelapp.feature.favorites.data.FavoriteDataSourceImpl
import com.app.marvelapp.feature.favorites.domain.DeleteFavoriteUseCase
import com.app.marvelapp.feature.favorites.domain.GetFavoritesUseCase

import com.app.marvelapp.feature.listofcharacters.domain.GetCharactersUseCase
import com.app.marvelapp.feature.listofcharacters.domain.SaveFavoriteUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var listOfCharactersModule = module {
    viewModel {
        ListOfCharactersViewModel(get(),get(), get(), get(), get())
    }
    factory {
        GetCharactersUseCase(get())
    }
    factory {
        GetFavoritesUseCase(get())
    }
    factory {
        DeleteFavoriteUseCase(get())
    }
    factory<CharactersDataSource> { CharacterDatasourceImpl(get()) }

    factory<FavoriteDataSource> { FavoriteDataSourceImpl(get()) }
    factory { SaveFavoriteUseCase(get()) }

}