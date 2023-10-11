package com.untitled.unboxing.feature.main.home

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.component.Header
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.unboxingClickable
import java.text.NumberFormat
import java.util.Locale

@Composable
internal fun HomeScreen() {
    val items = listOf(
        Item(
            profileImageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
            title = "원턱 와이드 스웨트팬츠 그레이",
            count = 1140,
        ),
        Item(
            profileImageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
            title = "원턱 와이드 스웨트팬츠 그레이",
            count = 1140,
        ),
        Item(
            profileImageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
            title = "원턱 와이드 스웨트팬츠 그레이",
            count = 1140,
        ),
    )

    val scrollScale = rememberScrollState()

    /*val alpha by animateFloatAsState(
        targetValue = if (scrollScale.value >= 300) 0f
        else 1f,
        label = "",
    )

    LaunchedEffect(scrollScale.value) {
        Log.d("TEST", scrollScale.value.toString())
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UnboxingColor.Neutral95),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Header(
                leadingIconRes = R.drawable.ic_logo_header,
                trailingIconRes = listOf(R.drawable.ic_notification),
            )
        }
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(scrollScale),
        ) {
            Product(items = items)
            Spacer(modifier = Modifier.height(12.dp))
            Withdrawals(
                wareHousing = 40000,
                released = 30000,
                onReleasedClick = {},
                onWareHousingClick = {},
            )
        }
    }
}

private data class Item(
    val profileImageUrl: String,
    val title: String,
    val count: Long,
)

@Composable
private fun Product(
    items: List<Item>
) {
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
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
            items.forEach {
                ProductItem(
                    imageUrl = it.profileImageUrl,
                    title = it.title,
                    count = it.count,
                )
            }
        }
    }
}

@Composable
private fun Withdrawals(
    wareHousing: Long,
    released: Long,
    onWareHousingClick: () -> Unit,
    onReleasedClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(UnboxingColor.Neutral100)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Withdrawal(
            iconRes = R.drawable.ic_warehousing,
            title = stringResource(id = R.string.home_today_warehousing),
            count = wareHousing,
            buttonText = stringResource(id = R.string.home_warehousing),
            onClick = onWareHousingClick,
        )
        Withdrawal(
            iconRes = R.drawable.ic_released,
            title = stringResource(id = R.string.home_today_released),
            count = released,
            buttonText = stringResource(id = R.string.home_released),
            onClick = onReleasedClick
        )
    }
}

@Composable
private fun Withdrawal(
    @DrawableRes iconRes: Int,
    title: String,
    count: Long,
    buttonText: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = title,
                style = UnboxingTypo.body2,
                color = UnboxingColor.Neutral30,
            )
            Text(
                text = "${NumberFormat.getNumberInstance(Locale.US).format(count)}개",
                style = UnboxingTypo.body1,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        WithdrawalButton(
            text = buttonText,
            onClick = onClick,
        )
    }
}

@Composable
private fun WithdrawalButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = UnboxingColor.Neutral95,
                shape = RoundedCornerShape(8.dp),
            )
            .unboxingClickable(
                rippleEnabled = true,
                onClick = onClick,
            )
            .bounceClick()
            .padding(
                vertical = 8.dp,
                horizontal = 14.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = UnboxingTypo.subtitle1,
            color = UnboxingColor.Neutral30,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ProductItem(
    imageUrl: String,
    title: String,
    count: Long,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .size(102.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                style = UnboxingTypo.body2,
                color = UnboxingColor.Neutral30,
            )
            Text(
                text = "${NumberFormat.getNumberInstance(Locale.US).format(count)}개",
                style = UnboxingTypo.body1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}