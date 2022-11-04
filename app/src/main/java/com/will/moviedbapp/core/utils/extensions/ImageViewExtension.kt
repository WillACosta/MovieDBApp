package com.will.moviedbapp.core.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

/**
 * Load [uri] in the [ImageView].
 */
fun ImageView.load(uri: String?) {
    Glide.with(context)
        .load(uri)
        .transition(withCrossFade())
        .into(this)
}
