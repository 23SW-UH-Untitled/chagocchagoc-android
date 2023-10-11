package com.untitled.unboxing.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.untitled.unboxing.ui.theme.UnboxingColor

@Composable
internal fun Header(
    @DrawableRes leadingIconRes: Int?,
    trailingIconRes: List<Int>?,
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(UnboxingColor.Primary90),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIconRes?.run {
            Image(
                painter = painterResource(id = this),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            trailingIconRes?.run {
                forEach {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}