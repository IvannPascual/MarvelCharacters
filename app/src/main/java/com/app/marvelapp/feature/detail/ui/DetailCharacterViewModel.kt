package com.app.marvelapp.feature.detail.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.marvelapp.base.ui.BaseViewModel
import com.app.marvelapp.common.result.mapError
import com.app.marvelapp.common.result.onSuccess
import com.app.marvelapp.feature.detail.domain.GetCharacterDetailUseCase
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailCharacterViewModel(
    private val detailCharacterId: Long,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
) : BaseViewModel() {

    val _characterDetailInfo = MutableStateFlow(MarvelCharacter())
    val characterDetailInfo = _characterDetailInfo.asLiveData()
    val _displayNoDescription = MutableStateFlow(false)
    val displayNoDescription = _displayNoDescription.asLiveData()

    fun getDetail() {
        _loading.value = true
        viewModelScope.launch {
            getCharacterDetailUseCase.invoke(detailCharacterId).onSuccess {
                _loading.value = false
                _characterDetailInfo.value = it
                _displayNoDescription.value = it.description.isEmpty()
            }.mapError {
                _loading.value = false
                _shouldDisplayError.value = true
                _displayError.value = it
            }
        }
    }
}