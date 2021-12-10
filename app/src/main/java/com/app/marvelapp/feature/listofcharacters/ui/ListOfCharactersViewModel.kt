package com.app.marvelapp.feature.listofcharacters.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.marvelapp.base.ui.BaseViewModel
import com.app.marvelapp.common.navigation.Navigator
import com.app.marvelapp.common.result.mapError
import com.app.marvelapp.common.result.onSuccess
import com.app.marvelapp.feature.favorites.domain.DeleteFavoriteUseCase
import com.app.marvelapp.feature.favorites.domain.Favorite
import com.app.marvelapp.feature.favorites.domain.GetFavoritesUseCase
import com.app.marvelapp.feature.favorites.domain.toMarvelCharacter
import com.app.marvelapp.feature.listofcharacters.domain.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ListOfCharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val navigator: Navigator
) : BaseViewModel() {

    private val _listToDisplay: MutableStateFlow<List<MarvelCharacter>?> =
        MutableStateFlow(listOf())
    val listToDisplay = _listToDisplay.asLiveData()


    private val _listOfCharacters = MutableStateFlow(listOf<MarvelCharacter>())
    val listOfCharacters = _listOfCharacters.asLiveData()
    private val favoritesClicked = MutableStateFlow(false)
    private val favorites = getFavoritesUseCase.invoke(Unit)
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    val displayNoFavorites = combine(favorites, favoritesClicked) { favorites, favoritesClicked ->
        favorites.isEmpty() && favoritesClicked
    }.asLiveData()

    val shouldDisplayError =
        combine(_shouldDisplayError, displayNoFavorites.asFlow()) { shouldDisplay, noFav ->
            shouldDisplay && !noFav
        }.asLiveData()

    fun getListOfCharacters() = _listOfCharacters

    val result = combine(
        favoritesClicked,
        favorites,
        _listOfCharacters
    ) { isClicked, favorites, characters ->
        if (isClicked) {
            _listToDisplay.value = showOnlyFavorites(favorites)
        } else {
            _listToDisplay.value = showAllCharactersWithFavorites(favorites, characters)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf<MarvelCharacter>())

    private fun showOnlyFavorites(favorites: List<Favorite>) =
        favorites.map { it.toMarvelCharacter(isFavorite = true) }

    private fun showAllCharactersWithFavorites(
        favorites: List<Favorite>,
        characters: List<MarvelCharacter>
    ): List<MarvelCharacter>  {
        val favoriteIds = favorites.map { it.id }
        val output = characters.map { currentCharacterGrid ->
            if (favoriteIds.contains(currentCharacterGrid.id)) {
                currentCharacterGrid.markAsFavorite(true)
            } else {
                currentCharacterGrid.markAsFavorite(false)
            }
        }
        return output
    }

    fun getCharacters() {
        _loading.value = true
        _shouldDisplayError.value = false
        viewModelScope.launch {
            getCharactersUseCase.invoke(Unit).onSuccess {
                _loading.value = false
                _listOfCharacters.value = it

            }.mapError {
                _displayError.value = it
                _shouldDisplayError.value = true
                _loading.value = false
            }
        }
    }

    fun openDetail(characterId: Long) {
        navigator.goTo(ListOfCharactersFragmentDirections.navToDetail(characterId))
    }

    fun onFavoriteClicked(marvelCharacter: MarvelCharacter) {
        viewModelScope.launch {
            val favorite = favorites.value.find { it.id == marvelCharacter.id }
            if (favorite == null) {
                saveFavoriteUseCase.invoke(marvelCharacter.toFavorite())
            } else {
                deleteFavoriteUseCase.invoke(marvelCharacter.id)
            }
        }
    }

    fun loadFavorites() {
        favoritesClicked.value = true
    }

    fun navBack() {
        if (favoritesClicked.value) {
            favoritesClicked.value = false
        } else {
            navigator.exit()
        }
    }

}