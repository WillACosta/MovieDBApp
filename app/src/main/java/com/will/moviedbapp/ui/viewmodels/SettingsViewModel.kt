package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.storage.AppDataStorage
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferencesRepository: AppDataStorage) :
    ViewModel() {
    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    init {
        readPreferences()
    }

    fun handleDarkMode(isDarkMode: Boolean) {
        _isDarkMode.value = isDarkMode

        viewModelScope.launch {
//            preferencesRepository.updateDarkMode(isDarkMode)
        }
    }

    private fun readPreferences() {
        viewModelScope.launch {
//            preferencesRepository
//                .getPreferences()
//                .collect {
//                    _isDarkMode.postValue(it.isDarkMode)
//                }
        }
    }
}
