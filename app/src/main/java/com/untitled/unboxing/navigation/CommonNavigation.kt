package com.untitled.unboxing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.untitled.unboxing.feature.Root
import com.untitled.unboxing.feature.splash.SplashScreen

internal fun NavGraphBuilder.commonNavigation() {
    navigation(
        startDestination = NavigationRoute.Common.Splash,
        route = NavigationRoute.Common.route,
    ) {
        composable(route = NavigationRoute.Common.Splash) {
            SplashScreen()
        }

        composable(route = NavigationRoute.Common.Root) {
            Root()
        }
    }
}