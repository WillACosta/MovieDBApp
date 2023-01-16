package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.core.model.ThemeConfig
import com.will.moviedbapp.core.model.UserData

object MockPreferences {
    val userData = UserData(
        name = "Jane",
        shouldHideWelcome = false,
        currentTheme = ThemeConfig.DARK
    )
}
