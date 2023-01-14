package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WelcomeViewModel(val repository: UserPreferencesRepository) : ViewModel() {

    val userPreferences = repository.userData.map {
        WelcomeUiStateData(
            it.name,
            it.isUserFirstTime
        )
    }

    fun setUserFirstTime() {
        viewModelScope.launch {
            repository.setUserFirstTime(false)
        }
    }
}

data class WelcomeUiStateData(
    val userName: String,
    val isFirstAccess: Boolean
)
