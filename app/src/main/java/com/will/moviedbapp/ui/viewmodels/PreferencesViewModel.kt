package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.*
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import com.will.moviedbapp.core.model.UserData
import kotlinx.coroutines.launch

class PreferencesViewModel(val repository: UserPreferencesRepository) : ViewModel() {

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    val greetingUser = Transformations
        .map(userData) { prefs ->
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

        }
    }
}
