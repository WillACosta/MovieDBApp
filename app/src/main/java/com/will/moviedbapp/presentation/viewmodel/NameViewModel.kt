package com.will.moviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.repository.userPreferences.UserPreferencesRepository
import kotlinx.coroutines.launch

class NameViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun onNameChanged(value: String) {
        _name.value = value
        _error.value = validate()
    }

    fun submitName() {
        if (_error.value == null) {
            viewModelScope.launch {
                repository.updateUserName(name.value!!)
            }
        }
    }

    private fun validate(): String? {
        val value = _name.value

        if (value != null && value.isEmpty() || value == null) {
            return "Oops! Your name is invalid"
        }

        if (value.length < 4) {
            return "Oops! Your name should not be less than 3 characters"
        }

        return null
    }

}
