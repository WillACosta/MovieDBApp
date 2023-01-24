package com.will.moviedbapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkAppColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}
