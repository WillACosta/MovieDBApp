package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NameViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val userName = MutableLiveData<String>()

    val nameError = MutableStateFlow<String?>(null)
        .combine(userName.asFlow()) { _, name ->
            name.validateUserName()
        }

    fun onNameChanged(value: String) {
        userName.postValue(value)
    }

    fun submitName() {
        viewModelScope.launch {
            userName.value?.let { userPreferencesRepository.updateUserName(it) }
        }
    }

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
