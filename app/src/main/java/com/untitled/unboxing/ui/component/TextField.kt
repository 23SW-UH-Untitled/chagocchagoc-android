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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.untitled.unboxing.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UnboxingTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester by remember { mutableStateOf(FocusRequester.Default) }

    Column {
        androidx.compose.material3.TextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused },
            value = text,
            textStyle = UnboxingTypo.h5.copy(
                fontWeight = FontWeight.Medium
            ),
            onValueChange = onTextChange,
            enabled = enabled,
            label = {
                label?.let {
                    androidx.compose.material3.Text(
                        text = it,
                        style = UnboxingTypo.subtitle1.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            },
            placeholder = {
                placeholder?.let {
                    androidx.compose.material3.Text(
                        text = it,
                        style = UnboxingTypo.h5.copy(
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
            },
            singleLine = singleLine,
            maxLines = maxLines,
            colors = TextFieldDefaults.textFieldColors(
                textColor = UnboxingColor.Neutral10,
                focusedLabelColor = UnboxingColor.Primary40,
                focusedSupportingTextColor = UnboxingColor.Neutral50,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = UnboxingColor.Neutral50,
                unfocusedSupportingTextColor = UnboxingColor.Neutral50,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = UnboxingColor.Primary40,
                containerColor = Color.Transparent
            ),
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = if (isFocused) trailingIcon else null
        )

        Divider(
            modifier = modifier
                .fillMaxWidth().padding(horizontal = 15.dp),
            color = if (isFocused) UnboxingColor.Primary40 else UnboxingColor.Neutral50
        )
    }
}
