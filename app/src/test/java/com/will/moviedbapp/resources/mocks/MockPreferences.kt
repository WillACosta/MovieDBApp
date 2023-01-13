package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.network.model.UserPreferences

object MockPreferences {
    val userPreferences = UserPreferences(
        name = "Jane",
        isUserFirstTime = false,
        currentTheme = "dark"
    )
}
