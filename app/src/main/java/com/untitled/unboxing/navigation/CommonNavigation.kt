package com.untitled.unboxing.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.untitled.unboxing.feature.Root
import com.untitled.unboxing.feature.inputwithdrawal.InputReceivingScreen
import com.untitled.unboxing.feature.inputwithdrawal.InputReleasingScreen
import com.untitled.unboxing.feature.productdetail.ProductDetailScreen
import com.untitled.unboxing.feature.registerproduct.RegisterProduct
import com.untitled.unboxing.feature.scanbarcode.ScanBarcodeScreen
import com.untitled.unboxing.feature.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
internal fun NavGraphBuilder.commonNavigation(
    navigateToRegisterProduct: () -> Unit,
    navigateToProductDetail: () -> Unit,
    navigateToInputReleasing: () -> Unit,
    navigateToInputReceiving: () -> Unit,
    popBackStack: () -> Unit,
    navController: NavController,
) {
    navigation(
        startDestination = NavigationRoute.Common.Root,
        route = NavigationRoute.Common.route,
    ) {
        composable(route = NavigationRoute.Common.Splash) {
            SplashScreen(navController = navController)
        }

        composable(route = NavigationRoute.Common.Root) {
            Root(
                navigateToRegisterProduct = navigateToRegisterProduct,
                navigateToProductDetail = navigateToProductDetail
            )
        }

        composable(route = NavigationRoute.Common.RegisterProduct) {
            RegisterProduct(popBackStack = popBackStack)
        }

        composable(route = NavigationRoute.Common.ProductDetail) {
            ProductDetailScreen(
                navigateToInputReceiving = navigateToInputReceiving,
                navigateToInputReleasing = navigateToInputReleasing,
                popBackStack = popBackStack
            )
        }

        composable(route = NavigationRoute.Common.ScanBarcode) {
            ScanBarcodeScreen(popBackStack = popBackStack)
        }

        composable(route = NavigationRoute.Common.InputReceiving) {
            InputReceivingScreen(popBackStack = popBackStack)
        }

        composable(route = NavigationRoute.Common.InputReleasing) {
            InputReleasingScreen(popBackStack = popBackStack)
        }
    }
}