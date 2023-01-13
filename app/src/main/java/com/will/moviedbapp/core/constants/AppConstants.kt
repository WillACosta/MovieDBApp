package com.will.moviedbapp.core.constants

abstract class AppConstants {

    object Network {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        const val LOG_RESPONSE_TAG = "HTTP_RESPONSE"
    }

    object Preferences {
        const val NAME = "user_preferences"
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
