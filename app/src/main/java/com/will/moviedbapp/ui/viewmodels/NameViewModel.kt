package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NameViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val userName = MutableStateFlow("")

    val nameError = MutableStateFlow<String?>(null)
        .combine(userName) { _, name ->
            name.validateUserName()
        }
        .onCompletion {
            userPreferencesRepository.updateUserName(userName.value)
        }

    fun onNameChanged(value: String) {
        viewModelScope.launch { userName.emit(value) }
    }

    fun submitName() {}

    fun String.validateUserName(): String? {
        if (isEmpty()) {
            return AppConstants.ValidationMessages.INVALID_NAME
        }

        if (length < 4) {
            return AppConstants.ValidationMessages.LESS_THAN_THREE_CHARACTERS
        }

        return null
    }
}
