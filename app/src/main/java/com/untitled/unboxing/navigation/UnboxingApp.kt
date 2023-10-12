package com.untitled.unboxing.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun UnboxingApp() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.Common.route,
    ) {
        commonNavigation(
            navigateToRegisterProduct = { navHostController.navigate(NavigationRoute.Common.RegisterProduct) },
            popBackStack = { navHostController.popBackStack() }
        )
    }
}