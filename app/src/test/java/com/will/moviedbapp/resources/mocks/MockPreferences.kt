package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.core.model.UserData

object MockPreferences {
    val userData = UserData(
        name = "Jane",
        isUserFirstTime = false,
        currentTheme = "dark"
    )
}
