package com.will.moviedbapp.data.repository.userPreferences

import com.will.moviedbapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun updateNotFirsAccess(isNotFirsAccess: Boolean)
    suspend fun updateDarkMode(isDarkMode: Boolean)
    suspend fun updateUserName(name: String)

    suspend fun getPreferences(): Flow<UserPreferences>
}
