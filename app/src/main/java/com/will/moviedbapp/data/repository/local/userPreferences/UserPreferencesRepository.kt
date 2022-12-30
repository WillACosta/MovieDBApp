package com.will.moviedbapp.data.repository.local.userPreferences

import com.will.moviedbapp.network.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun updateNotFirsAccess(isNotFirsAccess: Boolean)
    suspend fun updateDarkMode(isDarkMode: Boolean)
    suspend fun updateUserName(name: String)

    suspend fun getPreferences(): Flow<UserPreferences>
}
