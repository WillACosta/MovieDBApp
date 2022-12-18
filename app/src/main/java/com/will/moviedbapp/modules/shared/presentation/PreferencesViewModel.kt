package com.will.moviedbapp.modules.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.modules.shared.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.modules.shared.model.UserPreferences
import kotlinx.coroutines.launch

class PreferencesViewModel(val repository: UserPreferencesRepository) : ViewModel() {

    private val _userPreferences = MutableLiveData<UserPreferences>()
    val userPreferences: LiveData<UserPreferences> = _userPreferences

    init {
        getPreferences()
    }

    private fun getPreferences() {
        viewModelScope.launch {
            repository.getPreferences()
                .collect {
                    _userPreferences.postValue(it)
                }
        }
    }

    fun updateIsDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            repository.updateDarkMode(isDarkMode)
        }
    }
}
