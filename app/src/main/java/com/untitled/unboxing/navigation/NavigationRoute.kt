package com.untitled.unboxing.navigation

internal sealed class NavigationRoute(val route: String) {
    object Common : NavigationRoute(route = "common") {
        const val Splash = "splash"
        const val Root = "root"
        const val RegisterProduct = "registerProduct"
<<<<<<< HEAD
        const val ProductDetail = "productDetail"
=======
        const val ScanBarcode = "scanBarcode"
>>>>>>> feature/home
    }

    object Main : NavigationRoute(route = "main") {
        const val Home = "home"
        const val Product = "product"
        const val Withdrawal = "withdrawal"
        const val Stats = "stats"
        const val My = "my"
    }
}