package com.will.moviedbapp.core.validation

data class UserNameFormValidationState(
    val name: String = "",
    val nameError: String? = null,
)
