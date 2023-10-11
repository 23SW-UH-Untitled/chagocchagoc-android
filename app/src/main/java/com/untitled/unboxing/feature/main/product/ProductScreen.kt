package com.untitled.unboxing.feature.main.product

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor

@Composable
internal fun ProductScreen(){

    Log.d("SS", "ProductScreen: ")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(22.dp)
            ) {
                Icon(
                    tint = UnboxingColor.Neutral60,
                    painter = painterResource(id = R.drawable.ic_magnifyingglass),
                    contentDescription = null,
                )
            }
            
            Spacer(modifier = Modifier.width(25.dp))

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(22.dp)
            ) {
                Icon(
                    tint = UnboxingColor.Neutral60,
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    }
}
