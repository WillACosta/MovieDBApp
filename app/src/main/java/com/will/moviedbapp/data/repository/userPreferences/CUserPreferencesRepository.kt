package com.will.moviedbapp.data.repository.userPreferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class CUserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override suspend fun updateNotFirsAccess(isNotFirsAccess: Boolean) {
        dataStore.edit { preferences ->
            preferences[AppConstants.Preferences.NOT_FIRST_ACCESS_KEY] = isNotFirsAccess
        }
    }

    override suspend fun updateUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[AppConstants.Preferences.NAME_KEY] = name
        }
    }

    override suspend fun getPreferences(): Flow<UserPreferences> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(
                        AppConstants.LogTags.PREFERENCES,
                        AppConstants.ErrorMessages.PREFERENCES_DATA_STORE,
                        exception
                    )
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                mapUserPreferences(it)
            }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val userName = preferences[AppConstants.Preferences.NAME_KEY] ?: ""
        val isNotFirstAccess = preferences[AppConstants.Preferences.NOT_FIRST_ACCESS_KEY] ?: false

        return UserPreferences(userName, isNotFirstAccess)
    }

}
