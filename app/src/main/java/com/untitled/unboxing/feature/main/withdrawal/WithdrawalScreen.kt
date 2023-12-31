package com.untitled.unboxing.feature.main.withdrawal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.untitled.unboxing.R
import com.untitled.unboxing.feature.main.home.ProductItem
import com.untitled.unboxing.ui.component.Header
import com.untitled.unboxing.ui.component.LineChart
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.unboxingClickable
import java.time.LocalDateTime

@Composable
internal fun WithdrawalScreen(
    navigateToRegisterProduct: () -> Unit,
) {

    var currentYear by remember { mutableIntStateOf(LocalDateTime.now().year) }
    var currentMonth by remember { mutableIntStateOf(LocalDateTime.now().monthValue) }

    val onLeftClick = {
        when (currentMonth) {
            in 2..12 -> currentMonth -= 1
            else -> {
                currentYear -= 1
                currentMonth = 12
            }
        }
    }

    val onRightClick = {
        when (currentMonth) {
            in 1..11 -> currentMonth += 1
            else -> {
                currentYear += 1
                currentMonth = 1
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp,
            )
            .systemBarsPadding(),
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Header(
            leadingIconRes = null,
            trailingIconRes = listOf(
                R.drawable.ic_search,
                R.drawable.ic_barcode,
            ),
            onClicks = listOf(
                null,
                navigateToRegisterProduct,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "입고/출고",
            fontWeight = FontWeight.Bold,
            style = UnboxingTypo.h4.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Calendar(
            year = currentYear,
            month = currentMonth,
            onLeftClick = onLeftClick,
            onRightClick = onRightClick,
            onClick = {},
        )
        Spacer(modifier = Modifier.height(24.dp))
        LineChart(
            data = listOf(
                Pair("10.1", 30.0),
                Pair("10.12", 500.0),
                Pair("10.15", 500.0),
                Pair("10.24", 500.0),
                Pair("10.26", 455.0),
                Pair("10.27", 455.0),
                Pair("10.28", 455.0),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Spacer(modifier = Modifier.height(42.dp))
        Row {
            Text(
                text = "${currentMonth}월 총 재고량",
                color = UnboxingColor.Neutral40,
                style = UnboxingTypo.subtitle1,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "54,000개",
                color = UnboxingColor.Neutral20,
                style = UnboxingTypo.subtitle1,
            )
        }
        Withdrawals(
            list = listOf(
                1 to listOf(
                    Product(
                        imageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
                        productType = ProductType.WAREHOUSING,
                        title = "원턱 와이드 스웨트팬츠 그레이",
                        count = 40000,
                    ),
                    Product(
                        imageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
                        productType = ProductType.RELEASE,
                        title = "원턱 와이드 스웨트팬츠 그레이",
                        count = 40000,
                    ),
                    Product(
                        imageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
                        productType = ProductType.WAREHOUSING,
                        title = "원턱 와이드 스웨트팬츠 그레이",
                        count = 40000,
                    ),
                ),
                2 to listOf(
                    Product(
                        imageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
                        productType = ProductType.WAREHOUSING,
                        title = "원턱 와이드 스웨트팬츠 그레이",
                        count = 40000,
                    ),
                    Product(
                        imageUrl = "https://image.msscdn.net/images/goods_img/20210906/2112059/2112059_1_500.jpg",
                        productType = ProductType.RELEASE,
                        title = "원턱 와이드 스웨트팬츠 그레이",
                        count = 40000,
                    ),
                )
            )
        )
    }
}

@Composable
private fun Withdrawals(
    list: List<Pair<Int, List<Product>>>,
) {
    LazyColumn {
        items(list) {
            Withdrawal(
                day = it.first,
                week = "수",
                products = it.second,
            )
        }
        item{
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

internal enum class ProductType(val type: String) {
    WAREHOUSING("입고"),
    RELEASE("출고"),
}

private data class Product(
    val imageUrl: String,
    val productType: ProductType,
    val title: String,
    val count: Long,
)

@Composable
private fun Withdrawal(
    day: Int,
    week: String,
    products: List<Product>,
) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = "${day}일 ${week}요일",
        style = UnboxingTypo.subtitle1,
    )
    Spacer(modifier = Modifier.height(24.dp))
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        products.forEach {
            ProductItem(
                productType = it.productType,
                imageUrl = it.imageUrl,
                title = it.title,
                count = it.count,
                onClick = {},
            )
        }
    }
}

@Composable
private fun Calendar(
    year: Int,
    month: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.unboxingClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            modifier = Modifier.unboxingClickable(onClick = onLeftClick),
            painter = painterResource(id = R.drawable.ic_arrow_left_calendar),
            contentDescription = null,
        )
        Box(
            modifier = Modifier.width(110.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "${year}년 ${month}월",
                style = UnboxingTypo.body1,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
            )
        }
        Image(
            modifier = Modifier.unboxingClickable(onClick = onRightClick),
            painter = painterResource(id = R.drawable.ic_arrow_right_calendar),
            contentDescription = null,
        )
    }
}