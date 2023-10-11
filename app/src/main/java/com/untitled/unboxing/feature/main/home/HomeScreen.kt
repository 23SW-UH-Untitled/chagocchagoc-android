package com.untitled.unboxing.feature.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.component.Header
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo

@Composable
internal fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UnboxingColor.Neutral95),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Header(
                leadingIconRes = R.drawable.ic_logo_header,
                trailingIconRes = listOf(R.drawable.ic_notification),
            )
        }
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            Product()
        }
    }
}

@Composable
private fun Product() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(UnboxingColor.Neutral100)
            .padding(24.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.stock),
                style = UnboxingTypo.h6,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(14.dp))

    }
}

@Composable
private fun ProductItem(

){

}