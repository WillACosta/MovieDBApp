package com.will.moviedbapp.data.repository

import com.will.moviedbapp.core.model.ThemeConfig
import com.will.moviedbapp.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val userData: Flow<UserData>

    suspend fun updateUserName(name: String)
    suspend fun updateCurrentTheme(theme: ThemeConfig)
    suspend fun setShouldHideWelcome(shouldHideWelcome: Boolean)
}
