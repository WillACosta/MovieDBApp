package com.will.moviedbapp.modules.shared.presentation

import androidx.lifecycle.*
import com.will.moviedbapp.modules.shared.data.repository.local.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.modules.shared.model.UserPreferences
import kotlinx.coroutines.launch

class PreferencesViewModel(val repository: UserPreferencesRepository) : ViewModel() {

    private val _userPreferences = MutableLiveData<UserPreferences>()
    val userPreferences: LiveData<UserPreferences> = _userPreferences

    val greetingUser = Transformations
        .map(userPreferences) { prefs ->
            buildString {
                append("Hello, ")
                append(prefs.name)
                append("!")
            }
        }

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
