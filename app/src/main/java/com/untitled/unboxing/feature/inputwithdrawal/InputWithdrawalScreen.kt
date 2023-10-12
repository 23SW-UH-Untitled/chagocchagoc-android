package com.untitled.unboxing.feature.inputwithdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.untitled.unboxing.R
import com.untitled.unboxing.ui.component.LargeButton
import com.untitled.unboxing.ui.component.UnboxingTextField
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick

@Composable
internal fun InputReceivingScreen() {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(25.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(22.dp, 17.dp),
                    tint = UnboxingColor.Neutral0,
                    painter = painterResource(id = R.drawable.ic_direction_left),
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "입고 수량을 입력해주세요",
                style = UnboxingTypo.h5.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = UnboxingColor.Neutral10
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "남은 재고량",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral30
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "6,043개",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = UnboxingColor.Neutral30
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
        UnboxingTextField(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = text,
            onTextChange = { text = it },
            label = "개수입력",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
        )
        
        Spacer(modifier = Modifier.weight(1f))

        LargeButton(
            text = "입고하기",
            modifier = Modifier.padding(horizontal = 24.dp)
                .bounceClick()
        ) {
            // TODO
        }
        
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun InputReleasingScreen() {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(25.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(22.dp, 17.dp),
                    tint = UnboxingColor.Neutral0,
                    painter = painterResource(id = R.drawable.ic_direction_left),
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "출고 수량을 입력해주세요",
                style = UnboxingTypo.h5.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = UnboxingColor.Neutral10
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "남은 재고량",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral30
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "6,043개",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = UnboxingColor.Neutral30
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
        UnboxingTextField(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = text,
            onTextChange = { text = it },
            label = "개수입력",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
        )

        Spacer(modifier = Modifier.weight(1f))

        LargeButton(
            text = "출고하기",
            modifier = Modifier.padding(horizontal = 24.dp)
                .bounceClick()
        ) {
            // TODO
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}