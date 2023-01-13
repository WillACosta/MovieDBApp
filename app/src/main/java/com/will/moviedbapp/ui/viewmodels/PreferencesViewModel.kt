package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.*
import com.will.moviedbapp.data.storage.AppDataStorage
import com.will.moviedbapp.network.model.UserPreferences
import kotlinx.coroutines.launch

class PreferencesViewModel(val repository: AppDataStorage) : ViewModel() {

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
            repository.getUserName()
                .collect {

                }
        }
    }
}
