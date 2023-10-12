package com.untitled.unboxing.feature.registerproduct

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.unboxingClickable
import kotlinx.coroutines.launch

private enum class SheetType {
    BARCODE,
    PICTURE,
}

private enum class PermissionType {
    CAMERA,
    GALLERY,
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterialApi::class)
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

    var barcode by remember { mutableStateOf("") }

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    val hideBottomSheet: () -> Unit = {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    val showBottomSheet: () -> Unit = {
        coroutineScope.launch {
            bottomSheetState.show()
        }
    }

    val onBarcodeClick: (String) -> Unit = { value: String ->
        barcode = value
        hideBottomSheet()
    }

    var permissionType by remember { mutableStateOf(PermissionType.GALLERY) }

    var selectedImage: Uri? by remember { mutableStateOf(null) }

    var takenPictureBitmap: Bitmap? by remember { mutableStateOf(null) }

    val imageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
                selectedImage = it
            }
        }

    val takePictureLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            takenPictureBitmap = it
            hideBottomSheet()
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                when (permissionType) {
                    PermissionType.GALLERY -> imageLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly,
                        )
                    )

                    else -> takePictureLauncher.launch()
                }

            }
        }

    var sheetType by remember { mutableStateOf(SheetType.PICTURE) }

    val showBarcodeBottomSheet: () -> Unit = {
        sheetType = SheetType.BARCODE
        showBottomSheet()
    }

    val showPictureBottomSheet: () -> Unit = {
        sheetType = SheetType.PICTURE
        showBottomSheet()
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (sheetType == SheetType.BARCODE) {
                BarcodeBottomSheet(onBarcodeClick)
            } else {
                PictureBottomSheet(
                    onCameraClick = {
                        permissionType = PermissionType.CAMERA
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    },
                    onGalleryClick = {
                        imageLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly,
                            )
                        )
                    },
                )
            }
        },
        sheetState = bottomSheetState, sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        )
    ) {

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
                            onClick = {
                                showPictureBottomSheet()
                            },
                        ),
                    model = if (selectedImage != null) selectedImage
                    else if (takenPictureBitmap != null) takenPictureBitmap
                    else R.drawable.ic_photo,
                    contentScale = if (selectedImage == null) ContentScale.Fit
                    else ContentScale.Crop,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            RegisterProductInputs(
                name = { name },
                onNameChange = onNameChange,
                barcode = { barcode },
                purchase = { purchase },
                onPurchaseChange = onPurchaseChange,
                selling = { selling },
                onSellingChange = onSellingChange,
                showBottomSheet = showBarcodeBottomSheet,
            )
            Spacer(modifier = Modifier.weight(1f))
            ProductButton(
                text = "확인",
                onClick = {},
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
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
            .unboxingClickable(onClick = onClick)
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
    barcode: () -> String,
    purchase: () -> String,
    onPurchaseChange: (String) -> Unit,
    selling: () -> String,
    onSellingChange: (String) -> Unit,
    showBottomSheet: () -> Unit,
) {
    RegisterProductTextField(
        title = "제품명",
        value = name,
        hint = { "제품 이름을 입력하세요" },
        onValueChange = onNameChange,
    )
    RegisterProductTextField(
        title = "바코드",
        value = barcode,
        hint = { "바코드 입력방법" },
        onValueChange = onNameChange,
        drawable = R.drawable.ic_arrow_down,
        enabled = false,
        onClick = showBottomSheet,
    )
    RegisterProductTextField(
        title = "구매가",
        value = purchase,
        hint = { "구매가를 입력하세요(선택)" },
        onValueChange = onPurchaseChange,
        keyboardType = KeyboardType.NumberPassword,
    )
    RegisterProductTextField(
        title = "판매가",
        value = selling,
        hint = { "판매가를 입력하세요(선택)" },
        onValueChange = onSellingChange,
        keyboardType = KeyboardType.NumberPassword,
    )
}

@Composable
private fun RegisterProductTextField(
    title: String,
    value: () -> String,
    hint: () -> String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClick: (() -> Unit)? = null,
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
            .unboxingClickable(
                rippleEnabled = true,
                onClick = onClick ?: {},
            )
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                )
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

@Composable
private fun BarcodeBottomSheet(
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "바코드 입력방법을 선택해주세요",
            style = UnboxingTypo.h6,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(
            modifier = Modifier.unboxingClickable {
                onClick("바코드 스캔")
            },
            text = "바코드 스캔",
            style = UnboxingTypo.body1,
            color = UnboxingColor.Neutral50,
        )
        Text(
            modifier = Modifier.unboxingClickable {
                onClick("직접 입력")
            },
            text = "직접 입력",
            style = UnboxingTypo.body1,
            color = UnboxingColor.Neutral50,
        )
        Text(
            modifier = Modifier.unboxingClickable {
                onClick("바코드 자동 생성")
            },
            text = "바코드 자동 생성",
            style = UnboxingTypo.body1,
            color = UnboxingColor.Neutral50,
        )
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
private fun PictureBottomSheet(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
) {

    val intent = Intent()

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "사진 등록 방법을 선택해주세요",
            style = UnboxingTypo.h6,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .unboxingClickable(
                        rippleEnabled = true,
                        onClick = onCameraClick,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "사진 촬영",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral50,
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .unboxingClickable(
                        rippleEnabled = true,
                        onClick = onGalleryClick,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "사진 선택",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral50,
                )
            }
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}