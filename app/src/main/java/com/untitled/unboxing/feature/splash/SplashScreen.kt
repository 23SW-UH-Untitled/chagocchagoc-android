package com.untitled.unboxing.feature.splash

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.common.api.ApiException
import com.untitled.unboxing.GoogleApiContract
import com.untitled.unboxing.R
import com.untitled.unboxing.UnboxingApplication
import com.untitled.unboxing.navigation.NavigationRoute
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.unboxingClickable

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        if (UnboxingApplication.prefs.getIsLogin()) {
            navController.navigate(NavigationRoute.Common.Root)
        }
        viewModel.successLogin.collect {
            UnboxingApplication.prefs.apply {
                login()
                setString("accessToken", it.accessToken)
                setString("refreshToken", it.refreshToken)
            }
            navController.navigate(NavigationRoute.Common.Root)
        }
    }

    val authResultLauncher =
        rememberLauncherForActivityResult(GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
                    viewModel.login(gsa.idToken!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 169.dp,
                bottom = 54.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(191.dp, 212.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        SignInButton(
            onClick = {
                authResultLauncher.launch(1)
            },
            text = stringResource(id = R.string.sign_in_with_google),
        )
    }
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .bounceClick()
            .border(
                width = 1.dp,
                color = UnboxingColor.Neutral90,
                shape = CircleShape,
            )
            .unboxingClickable(onClick = onClick)
            .padding(
                horizontal = 36.dp,
                vertical = 14.dp,
            ),
    ) {
        Image(
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = UnboxingTypo.body1.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
    }
}
