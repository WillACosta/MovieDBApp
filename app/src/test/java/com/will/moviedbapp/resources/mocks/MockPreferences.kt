package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.domain.model.UserPreferences

object MockPreferences {
    val userPreferences = UserPreferences(
        name = "Jane",
        isNotFirsAccess = false,
        isDarkMode = false
    )
}
