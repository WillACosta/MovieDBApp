package com.will.moviedbapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.will.moviedbapp.ui.welcome.navigation.welcomeNavigationRoute
import com.will.moviedbapp.ui.welcome.navigation.welcomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = welcomeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        welcomeScreen()
    }
}
