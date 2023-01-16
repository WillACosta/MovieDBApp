package com.will.moviedbapp.core.model

data class UserData(
    val name: String,
    val currentTheme: ThemeConfig,
    val shouldHideWelcome: Boolean
)
