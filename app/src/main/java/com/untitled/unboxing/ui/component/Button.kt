package com.untitled.unboxing.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.NoRippleInteractionSource

@Composable
internal fun LargeButton(
    modifier: Modifier = Modifier,
    containerColor: Color = UnboxingColor.Primary40,
    contentColor: Color = UnboxingColor.Primary100,
    text: String,
    contentPadding: PaddingValues = PaddingValues(vertical = 18.dp),
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        interactionSource = NoRippleInteractionSource(),
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(containerColor),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = text,
            style = UnboxingTypo.body1.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = contentColor
        )
    }
}