package com.will.moviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.drop
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
