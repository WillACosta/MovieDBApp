package com.will.moviedbapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.will.moviedbapp.core.navigation.AppNavHost
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MovieDBApplication() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) {
        AppNavHost(navController, onBackClick = {})
    }
}
