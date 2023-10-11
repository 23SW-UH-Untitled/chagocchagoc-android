package com.untitled.unboxing.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo

@Composable
internal fun TextField(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIconRes: Int? = null,
    placeholder: String? = null,
    text: String,
    onTextChange: (String) -> Unit,
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = UnboxingColor.Neutral95, shape = RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        leadingIconRes?.let {
            Icon(
                modifier = Modifier.size(18.dp),
                tint = UnboxingColor.Neutral40,
                painter = painterResource(id = R.drawable.ic_magnifyingglass),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        BasicTextField(
            value = text.ifBlank { placeholder ?: "" },
            onValueChange = onTextChange,
            textStyle = UnboxingTypo.body2.copy(
                fontWeight = FontWeight.Medium,
                color = if (text.isBlank()) UnboxingColor.Neutral50 else UnboxingColor.Neutral50
            ),
            singleLine = true,
            maxLines = 1,
        )
    }
}