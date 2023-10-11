package com.untitled.unboxing.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.untitled.chagocchagoc.R

val aggroFamily = FontFamily(
    Font(R.font.sb_aggro_large, FontWeight.Normal),
    Font(R.font.sb_aggro_medium, FontWeight.Medium),
    Font(R.font.sb_aggro_bold, FontWeight.Bold),
    Font(R.font.sb_aggro_bold, FontWeight.SemiBold),
)

object UnboxingTypo {
    private val platFormTextStyle = PlatformTextStyle(
        includeFontPadding = false,
    )

    @Stable
    val h1 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 60.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val h2 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 54.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val h3 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val h4 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 40.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val h5 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val h6 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val body1 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val body2 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val subtitle1 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val subtitle2 = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val caption = TextStyle(
        fontFamily = aggroFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        platformStyle = platFormTextStyle,
    )

}