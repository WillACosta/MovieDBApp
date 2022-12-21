package com.will.moviedbapp.modules.movie.utils

import android.app.Activity
import android.os.Bundle
import com.will.moviedbapp.core.constants.AppRoutes
import com.will.moviedbapp.core.utils.extensions.navigateTo
import com.will.moviedbapp.modules.movie.domain.entity.Movie

object MovieFunctionsHelper {

    fun onTouchedMovieItem(activity: Activity?, movie: Movie) {
        val extras = Bundle().apply { putString("id", movie.id.toString()) }
        activity?.navigateTo(AppRoutes.MOVIE_DETAIL, extras)
    }
}
