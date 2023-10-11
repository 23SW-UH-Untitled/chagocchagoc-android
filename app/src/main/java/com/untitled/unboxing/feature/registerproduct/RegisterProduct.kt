package com.untitled.unboxing.feature.registerproduct

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.unboxingClickable

@Composable
internal fun RegisterProduct() {
    var name by remember { mutableStateOf("") }
    val onNameChange = { value: String ->
        name = value
    }

    var purchase by remember { mutableStateOf("") }
    val onPurchaseChange = { value: String ->
        purchase = value
    }

    var selling by remember { mutableStateOf("") }
    val onSellingChange = { value: String ->
        selling = value
    }

    var selectedImage: Uri? by remember { mutableStateOf(null) }

    val imageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                selectedImage = it
            }
        }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            imageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 14.dp,
                end = 14.dp,
            )
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_direction_left),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "입고/출고",
                style = UnboxingTypo.h4,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(38.dp))
            AsyncImage(
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .unboxingClickable(
                        rippleEnabled = true,
                        onClick = { launcher.launch(Manifest.permission.CAMERA) },
                    ),
                model = if (selectedImage == null) R.drawable.ic_photo
                else selectedImage,
                contentScale = if (selectedImage == null) ContentScale.Fit
                else ContentScale.Crop,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        RegisterProductInputs(
            name = { name },
            onNameChange = onNameChange,
            purchase = { purchase },
            onPurchaseChange = onPurchaseChange,
            selling = { selling },
            onSellingChange = onSellingChange,
        )
        Spacer(modifier = Modifier.weight(1f))
        ProductButton(
            text = "확인",
            onClick = {},
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ProductButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .unboxingClickable(
                rippleEnabled = true,
                onClick = onClick,
            )
            .bounceClick()
            .background(
                color = UnboxingColor.Primary40,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = UnboxingTypo.body1,
            color = UnboxingColor.Neutral100,
        )
    }
}

@Composable
private fun RegisterProductInputs(
    name: () -> String,
    onNameChange: (String) -> Unit,
    purchase: () -> String,
    onPurchaseChange: (String) -> Unit,
    selling: () -> String,
    onSellingChange: (String) -> Unit,
) {
    RegisterProductTextField(
        title = "제품명",
        value = name,
        hint = { "제품 이름을 입력하세요" },
        onValueChange = onNameChange,
    )
    RegisterProductTextField(
        title = "바코드",
        value = { "" },
        hint = { "바코드 입력방법" },
        onValueChange = onNameChange,
        drawable = R.drawable.ic_arrow_down,
        enabled = false,
    )
    RegisterProductTextField(
        title = "구매가",
        value = purchase,
        hint = { "구매가를 입력하세요(선택)" },
        onValueChange = onPurchaseChange,
    )
    RegisterProductTextField(
        title = "판매가",
        value = selling,
        hint = { "판매가를 입력하세요(선택)" },
        onValueChange = onSellingChange,
    )
}

@Composable
private fun RegisterProductTextField(
    title: String,
    value: () -> String,
    hint: () -> String,
    onValueChange: (String) -> Unit,
    drawable: Int? = null,
    enabled: Boolean = true,
) {
    Text(
        text = title,
        style = UnboxingTypo.subtitle1,
        color = UnboxingColor.Neutral30,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UnboxingColor.Neutral95,
                shape = RoundedCornerShape(12.dp),
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value(),
                onValueChange = onValueChange,
                textStyle = UnboxingTypo.body2,
                enabled = enabled,
            )
            if (value().isEmpty()) {
                Text(
                    text = hint(),
                    style = UnboxingTypo.body2,
                    color = UnboxingColor.Neutral60,
                )
            }
        }
        if (drawable != null) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}