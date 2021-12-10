package com.app.marvelapp.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.marvelapp.R
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {
    val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    val _displayError: MutableStateFlow<Int> = MutableStateFlow(R.string.api_error_unknown)
    val displayError = _displayError.asLiveData()

    val _shouldDisplayError = MutableStateFlow(false)
    val loading = _loading.asLiveData()
}
