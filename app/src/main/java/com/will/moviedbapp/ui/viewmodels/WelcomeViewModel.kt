package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.repository.user_preferences.UserPreferencesRepository
import com.will.moviedbapp.network.model.UserPreferences
import kotlinx.coroutines.launch

class WelcomeViewModel(val repository: UserPreferencesRepository) : ViewModel() {

    private val _userPreferences = MutableLiveData<UserPreferences>()
    val userPreferences: LiveData<UserPreferences> = _userPreferences

    init {
        viewModelScope.launch {
            repository.getPreferences()
                .collect {
                    _userPreferences.postValue(it)
                }
        }
    }

    fun handleFirstAccess() {
        viewModelScope.launch {
            repository.updateNotFirsAccess(true)
        }
    }
}
