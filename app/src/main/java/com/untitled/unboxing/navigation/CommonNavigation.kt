package com.untitled.unboxing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.untitled.unboxing.feature.Root
import com.untitled.unboxing.feature.registerproduct.RegisterProduct
import com.untitled.unboxing.feature.splash.SplashScreen

internal fun NavGraphBuilder.commonNavigation() {
    navigation(
        startDestination = NavigationRoute.Common.RegisterProduct,
        route = NavigationRoute.Common.route,
    ) {
        composable(route = NavigationRoute.Common.Splash) {
            SplashScreen()
        }

        composable(route = NavigationRoute.Common.Root) {
            Root()
        }

        composable(route = NavigationRoute.Common.RegisterProduct){
            RegisterProduct()
        }
    }
}