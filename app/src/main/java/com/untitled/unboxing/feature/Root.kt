package com.untitled.unboxing.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.untitled.unboxing.feature.main.home.HomeScreen
import com.untitled.unboxing.feature.main.my.MyScreen
import com.untitled.unboxing.feature.main.product.ProductScreen
import com.untitled.unboxing.feature.main.stats.StatsScreen
import com.untitled.unboxing.feature.main.withdrawal.WithdrawalScreen
import com.untitled.unboxing.navigation.NavigationRoute
import com.untitled.unboxing.ui.component.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Root(
    navigateToRegisterProduct: () -> Unit,
    navigateToProductDetail: () -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Main.route,
        ) {
            navigation(
                startDestination = NavigationRoute.Main.Home,
                route = NavigationRoute.Main.route,
            ) {
                composable(route = NavigationRoute.Main.Home) {
                    HomeScreen()
                }

                composable(route = NavigationRoute.Main.Product) {
                    ProductScreen(navigateToProductDetail = navigateToProductDetail)
                }

                composable(route = NavigationRoute.Main.Withdrawal) {
                    WithdrawalScreen(navigateToRegisterProduct = navigateToRegisterProduct)
                }

                composable(route = NavigationRoute.Main.Stats) {
                    StatsScreen()
                }

                composable(route = NavigationRoute.Main.My) {
                    MyScreen()
                }
            }
        }
    }
}