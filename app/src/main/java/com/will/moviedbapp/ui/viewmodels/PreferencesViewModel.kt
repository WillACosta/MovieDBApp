package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.*
import com.will.moviedbapp.data.repository.user_preferences.UserPreferencesRepository
import com.will.moviedbapp.network.model.UserPreferences
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
}
