package com.untitled.unboxing.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.untitled.chagocchagoc.R

internal sealed class NavigationItem(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
) {
    object Home : NavigationItem(
        route = NavigationRoute.Main.Home,
        iconRes = R.drawable.ic_home,
        labelRes = R.string.home,
    )

    object Product : NavigationItem(
        route = NavigationRoute.Main.Product,
        iconRes = R.drawable.ic_product,
        labelRes = R.string.product,
    )

    object Withdrawal : NavigationItem(
        route = NavigationRoute.Main.Withdrawal,
        iconRes = R.drawable.ic_withdrawal,
        labelRes = R.string.withdrawal,
    )

    object Stats : NavigationItem(
        route = NavigationRoute.Main.Stats,
        iconRes = R.drawable.ic_stats,
        labelRes = R.string.stats,
    )

    object My : NavigationItem(
        route = NavigationRoute.Main.My,
        iconRes = R.drawable.ic_my,
        labelRes = R.string.my,
    )
}