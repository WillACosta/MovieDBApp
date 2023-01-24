package com.will.moviedbapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.will.moviedbapp.R
import androidx.compose.ui.text.*
import java.time.format.TextStyle

private val Inter = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
)

private val Rajdhani = FontFamily(
    Font(R.font.rajdhani, FontWeight.Normal),
    Font(R.font.rajdhani_bold, FontWeight.Bold),
)

val AppTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.15.sp
    ),
)
