package com.will.moviedbapp.modules.shared.data.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.will.moviedbapp.core.constants.AppConstants

val Context.dataStore by preferencesDataStore(
    name = AppConstants.Preferences.NAME
)
