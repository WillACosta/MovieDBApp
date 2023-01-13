package com.will.moviedbapp.network.model

data class UserPreferences(
    val name: String,
    val isUserFirstTime: Boolean,
    val currentTheme: String
)
