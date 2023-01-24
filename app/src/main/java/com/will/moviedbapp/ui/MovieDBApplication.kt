package com.will.moviedbapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.will.moviedbapp.core.navigation.AppNavHost

@Preview
@Composable
fun MovieDBApplication() {
    val navController = rememberNavController()
    AppNavHost(navController, onBackClick = {})
}
