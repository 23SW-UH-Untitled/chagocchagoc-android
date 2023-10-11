package com.untitled.unboxing.navigation

internal sealed class NavigationRoute(val route: String) {
    object Common : NavigationRoute(route = "common") {
        const val Splash = "splash"
        const val Root = "root"
    }

    object Main : NavigationRoute(route = "main") {
        const val Home = "home"
        const val Product = "product"
        const val Withdrawal = "withdrawal"
        const val Stats = "stats"
        const val My = "my"
    }
}