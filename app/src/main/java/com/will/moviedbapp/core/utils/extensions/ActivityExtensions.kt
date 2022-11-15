package com.will.moviedbapp.core.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate

fun Activity.navigateTo(className: String, extras: Bundle? = null) {
    val intent = Intent()
    extras?.let { intent.putExtras(it) }
    intent.setClassName(this, className)

    startActivity(intent)
}

fun Activity.setDarkMode(isDarkMode: Boolean = true) {
    if (isDarkMode) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
