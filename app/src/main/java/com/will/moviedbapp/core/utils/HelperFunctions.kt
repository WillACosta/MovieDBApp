package com.will.moviedbapp.core.utils

import android.content.Context
import android.content.Intent

object HelperFunctions {
    fun <T> startActivity(context: Context, className: Class<T>) {
        context.startActivity(Intent(context, className))
    }
}
