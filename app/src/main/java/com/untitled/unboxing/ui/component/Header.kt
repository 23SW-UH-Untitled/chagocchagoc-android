package com.untitled.unboxing.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.untitled.unboxing.ui.util.unboxingClickable

@Composable
internal fun Header(
    @DrawableRes leadingIconRes: Int?,
    trailingIconRes: List<Int>?,
    onClicks: List<(() -> Unit)?>?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIconRes?.run {
            Image(
                modifier = Modifier.size(
                    width = 180.dp,
                    height = 33.dp,
                ),
                painter = painterResource(id = this),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            trailingIconRes?.run {
                forEachIndexed { index, element ->
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .unboxingClickable(onClick = onClicks?.get(index) ?: {}),
                        painter = painterResource(id = element),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}