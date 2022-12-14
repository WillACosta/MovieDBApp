package com.will.moviedbapp.core.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

abstract class AppConstants {

    object Network {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        const val LOG_RESPONSE_TAG = "HTTP_RESPONSE"
    }

    object Preferences {
        const val NAME = "user_preferences"
        val NAME_KEY = stringPreferencesKey("name")
        val NOT_FIRST_ACCESS_KEY = booleanPreferencesKey("isNotFirstAccess")
        val IS_DARK_MODE_KEY = booleanPreferencesKey("isDarkMode")
    }

    object LogTags {
        const val PREFERENCES = "user_preferences"
    }

    object ErrorMessages {
        const val PREFERENCES_DATA_STORE = "Error reading preferences."
    }

    object ValidationMessages {
        const val INVALID_NAME = "Oops! Your name is invalid"
        const val LESS_THAN_THREE_CHARACTERS =
            "Oops! Your name should not be less than 3 characters"
    }

    object AppMessages {
        const val NAME_EDIT_SUCCESS = "Your user name was update with successful!"
        const val GENERIC_ERROR = "Oops! Something was wrong..."
    }
}
