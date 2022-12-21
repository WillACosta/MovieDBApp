package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.modules.shared.model.UserPreferences

object MockPreferences {
    val userPreferences = UserPreferences(
        name = "Jane",
        isNotFirsAccess = false,
        isDarkMode = false
    )
}
