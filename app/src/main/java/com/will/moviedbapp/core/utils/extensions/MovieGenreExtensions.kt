package com.will.moviedbapp.core.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.will.moviedbapp.R
import com.will.moviedbapp.domain.entities.Genre

fun Genre.toChipComponent(context: Context, onTouchedGenre: (Int) -> Unit): Chip {
    val chipItem = LayoutInflater.from(context).inflate(
        R.layout.genre_list_item, null, false
    ) as Chip

    chipItem.text = description
    chipItem.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) onTouchedGenre(id)
    }

    return chipItem
}
