package com.will.moviedbapp.data.storage

import kotlinx.coroutines.flow.Flow

interface AppDataStorage {
    fun getUserName(): Flow<String>
    suspend fun updateUserName(name: String)

    fun getCurrentTheme(): Flow<String>
    suspend fun updateCurrentTheme(theme: String)

    fun isUserFirstTime(): Flow<Boolean>
    suspend fun setUserFirstTime(isFirstTime: Boolean)
}
