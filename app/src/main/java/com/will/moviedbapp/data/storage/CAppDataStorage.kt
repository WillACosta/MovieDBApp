package com.will.moviedbapp.data.storage

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.network.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class CAppDataStorage(private val dataStore: DataStore<Preferences>) : AppDataStorage {

    private object PreferencesKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val IS_USER_FIRST_TIME = booleanPreferencesKey("user_first_time")
        val CURRENT_THEME = stringPreferencesKey("current_theme")
    }

    override fun getUserName(): Flow<String> {
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
                it[PreferencesKeys.USER_NAME] ?: ""
            }
    }

    override suspend fun updateUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = name
        }
    }

    override fun getCurrentTheme(): Flow<String> {
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
                it[PreferencesKeys.CURRENT_THEME] ?: "dark"
            }
    }

    override suspend fun updateCurrentTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_THEME] = theme
        }
    }

    override fun isUserFirstTime(): Flow<Boolean> {
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
                it[PreferencesKeys.IS_USER_FIRST_TIME] ?: false
            }
    }

    override suspend fun setUserFirstTime(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_USER_FIRST_TIME] = isFirstTime
        }
    }

    suspend fun getPreferences(): Flow<UserPreferences> {
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
        val userName = preferences[PreferencesKeys.USER_NAME] ?: ""
        val isUserFirstTime = preferences[PreferencesKeys.IS_USER_FIRST_TIME] ?: false
        val currentTheme = preferences[PreferencesKeys.CURRENT_THEME] ?: "dark"

        return UserPreferences(userName, isUserFirstTime, currentTheme)
    }

}
