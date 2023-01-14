package com.will.moviedbapp.data.utils

import android.content.Context
import androidx.datastore.dataStore
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.data.storage.UserPreferencesSerializer

val Context.dataStore by dataStore(
    fileName = AppConstants.Preferences.NAME,
    serializer = UserPreferencesSerializer
)
