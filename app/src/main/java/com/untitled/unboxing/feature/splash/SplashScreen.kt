package com.untitled.unboxing.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.unboxingClickable

@Composable
internal fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 116.dp,
                bottom = 54.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = stringResource(id = R.string.unboxing),
                style = UnboxingTypo.h2,
                color = UnboxingColor.Primary40,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        SignInButton(
            onClick = {},
            text = stringResource(id = R.string.sign_in_with_google),
        )
    }
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
    text: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = UnboxingColor.Neutral70,
                shape = CircleShape,
            )
            .clip(CircleShape)
            .unboxingClickable(
                rippleEnabled = true,
                onClick = onClick,
            )
            .padding(
                horizontal = 36.dp,
                vertical = 14.dp,
            )
            .bounceClick(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(48.dp))
        Text(
            text = text,
            style = UnboxingTypo.body1,
        )
    }
}