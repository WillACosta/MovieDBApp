package com.will.moviedbapp.data.storage

import android.util.Log
import androidx.datastore.core.DataStore
import com.proto.ThemeConfigProto
import com.proto.UserPreferences
import com.will.moviedbapp.core.model.ThemeConfig
import com.will.moviedbapp.core.model.UserData
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.map
import java.io.IOException

class CUserPreferencesRepository(private val dataStore: DataStore<UserPreferences>) :
    UserPreferencesRepository {

    private object Tags {
        const val DATA_STORE = "AppDataStorage"
    }

    override val userData = dataStore.data.map {
        UserData(
            name = it.name,
            currentTheme = when (it.currentTheme) {
                null,
                ThemeConfigProto.THEME_CONFIG_UNSPECIFIED,
                ThemeConfigProto.UNRECOGNIZED,
                ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM -> ThemeConfig.FOLLOW_SYSTEM
                ThemeConfigProto.THEME_CONFIG_LIGHT -> ThemeConfig.LIGHT
                ThemeConfigProto.THEME_CONFIG_DARK -> ThemeConfig.DARK
            },
            isUserFirstTime = it.isUserFirstTime
        )
    }

    override suspend fun updateUserName(name: String) {
        try {
            dataStore.updateData {
                it.toBuilder().setName(name).build()
            }
        } catch (ioException: IOException) {
            Log.e(Tags.DATA_STORE, "Failed to update user preferences", ioException)
        }
    }

    override suspend fun updateCurrentTheme(theme: ThemeConfig) {
        try {
            val themeProto = when (theme) {
                ThemeConfig.FOLLOW_SYSTEM -> ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM
                ThemeConfig.LIGHT -> ThemeConfigProto.THEME_CONFIG_LIGHT
                ThemeConfig.DARK -> ThemeConfigProto.THEME_CONFIG_DARK
            }

            dataStore.updateData {
                it.toBuilder().setCurrentTheme(themeProto).build()
            }
        } catch (ioException: IOException) {
            Log.e(Tags.DATA_STORE, "Failed to update user preferences", ioException)
        }
    }

    override suspend fun setUserFirstTime(isFirstTime: Boolean) {
        try {
            dataStore.updateData {
                it.toBuilder().setIsUserFirstTime(isFirstTime).build()
            }
        } catch (ioException: IOException) {
            Log.e(Tags.DATA_STORE, "Failed to update user preferences", ioException)
        }
    }

}

