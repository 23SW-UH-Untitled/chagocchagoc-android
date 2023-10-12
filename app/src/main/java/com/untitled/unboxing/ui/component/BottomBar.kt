package com.untitled.unboxing.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.untitled.unboxing.navigation.NavigationItem
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.util.NoRippleInteractionSource
import com.untitled.unboxing.ui.util.bounceClick

@Composable
internal fun BottomBar(
    navController: NavController,
) {
    val tabs = listOf(
        NavigationItem.Home,
        NavigationItem.Product,
        NavigationItem.Withdrawal,
        NavigationItem.Stats,
        NavigationItem.My,
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .graphicsLayer(
                clip = true,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                ),
            )
            .border(
                width = 1.dp,
                color = UnboxingColor.Neutral95,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                )
            ),
        backgroundColor = UnboxingColor.Neutral100,
    ) {
        tabs.forEach {
            val selected = currentRoute == it.route

            val iconTint by animateColorAsState(
                targetValue = if (selected) UnboxingColor.Neutral10
                else UnboxingColor.Neutral50,
                label = "",
            )

            BottomNavigationItem(
                modifier = Modifier.bounceClick(),
                interactionSource = NoRippleInteractionSource(),
                selected = selected,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(bottom = 2.dp),
                        painter = painterResource(id = it.iconRes),
                        contentDescription = null,
                        tint = iconTint,
                    )
                },
                label = {
                    Text(
                        modifier = Modifier.padding(bottom = 12.dp),
                        text = stringResource(id = it.labelRes),
                        color = iconTint,
                        style = UnboxingTypo.caption
                    )
                }
            )
        }
    }
}