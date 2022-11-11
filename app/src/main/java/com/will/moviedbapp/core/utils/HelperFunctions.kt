package com.will.moviedbapp.core.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

object HelperFunctions {
    fun <T> startActivity(context: Context, className: Class<T>, extras: Bundle? = null) {
        val intent = Intent()
        extras?.let { intent.putExtras(it) }
        intent.setClass(context, className)

        context.startActivity(intent)
    }
}
