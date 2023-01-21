package com.will.moviedbapp.ui.welcome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.will.moviedbapp.ui.welcome.WelcomeScreen

const val welcomeNavigationRoute = "welcome_route"

fun NavGraphBuilder.welcomeScreen() {
    composable(welcomeNavigationRoute) {
        WelcomeScreen()
    }
}

fun NavController.navigateToWelcome(navOptions: NavOptions? = null) {
    this.navigate(welcomeNavigationRoute, navOptions)
}
