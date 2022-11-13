package com.will.moviedbapp.core.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.navigateTo(className: String, extras: Bundle? = null) {
    val intent = Intent()
    extras?.let { intent.putExtras(it) }
    intent.setClassName(this, className)

    startActivity(intent)
}
