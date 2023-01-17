package com.will.moviedbapp.core.utils.extensions

import com.will.moviedbapp.core.constants.AppConstants

fun String.validateUserName(): String? {
    if (isEmpty()) {
        return AppConstants.ValidationMessages.INVALID_NAME
    }

    if (length < 4) {
        return AppConstants.ValidationMessages.LESS_THAN_THREE_CHARACTERS
    }

    return null
}
