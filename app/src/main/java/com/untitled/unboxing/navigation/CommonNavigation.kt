package com.untitled.unboxing.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.untitled.unboxing.feature.Root
import com.untitled.unboxing.feature.registerproduct.RegisterProduct
import com.untitled.unboxing.feature.scanbarcode.ScanBarcodeScreen
import com.untitled.unboxing.feature.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
internal fun NavGraphBuilder.commonNavigation(
    navigateToRegisterProduct: () -> Unit,
    popBackStack: () -> Unit,
) {
    navigation(
        startDestination = NavigationRoute.Common.ScanBarcode,
        route = NavigationRoute.Common.route,
    ) {
        composable(route = NavigationRoute.Common.Splash) {
            SplashScreen()
        }

        composable(route = NavigationRoute.Common.Root) {
            Root(navigateToRegisterProduct = navigateToRegisterProduct)
        }

        composable(route = NavigationRoute.Common.RegisterProduct) {
            RegisterProduct(popBackStack = popBackStack)
        }

        composable(route = NavigationRoute.Common.ScanBarcode){
            ScanBarcodeScreen()
        }
    }
}