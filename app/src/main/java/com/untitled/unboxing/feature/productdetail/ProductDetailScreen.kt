package com.untitled.unboxing.feature.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.untitled.unboxing.R
import com.untitled.unboxing.ui.component.LargeButton
import com.untitled.unboxing.ui.component.LineChart
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo
import com.untitled.unboxing.ui.util.NoRippleInteractionSource
import com.untitled.unboxing.ui.util.bounceClick
import com.untitled.unboxing.ui.util.noRippleClickable
import com.untitled.unboxing.ui.util.unboxingClickable

@Composable
internal fun ProductDetailScreen(
    popBackStack: () -> Unit,
    navigateToInputReceiving: () -> Unit,
    navigateToInputReleasing: () -> Unit,
) {

    val scrollState = rememberScrollState()

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
                modifier = Modifier.size(25.dp).bounceClick(),
                onClick = popBackStack,
                interactionSource = NoRippleInteractionSource()
            ) {
                Icon(
                    modifier = Modifier.size(22.dp, 17.dp),
                    tint = UnboxingColor.Neutral0,
                    painter = painterResource(id = R.drawable.ic_direction_left),
                    contentDescription = null
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .bounceClick()
                    .unboxingClickable {

                    },
                text = "편집",
                style = UnboxingTypo.body1.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = UnboxingColor.Neutral30
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
        ) {
            AsyncImage(
                model = "https://shop-phinf.pstatic.net/20231005_78/1696502208560g5UHd_JPEG/8184287702687831_682110038.jpg",
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "머드 절개 벌룬 와이드 데님 팬츠 (2C)",
                    style = UnboxingTypo.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = UnboxingColor.Neutral10
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "6,043개",
                    style = UnboxingTypo.h6.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp
                    ),
                    color = UnboxingColor.Neutral10
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = UnboxingColor.Neutral95
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "바코드 확인하기",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = UnboxingColor.Neutral40
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    tint = UnboxingColor.Neutral70,
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = null,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "구매가",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = UnboxingColor.Neutral40
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "3,6000원",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral20
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "판매가",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = UnboxingColor.Neutral40
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "4,5160원",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Neutral20
                )
            }

            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = UnboxingColor.Neutral95,
                thickness = 18.dp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "이번달 수익",
                    style = UnboxingTypo.body1.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = UnboxingColor.Neutral40
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "215,000원",
                    style = UnboxingTypo.body1,
                    color = UnboxingColor.Primary40
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = UnboxingColor.Neutral95
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            LineChart(
                data = listOf(
                    Pair("10.1", 30.0),
                    Pair("10.2", 500.0),
                    Pair("10.3", 40.0),
                    Pair("10.4", 70.0),
                    Pair("10.5", 100.0),
                    Pair("10.6", 300.0),
                    Pair("10.7", 200.0),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LargeButton(
                modifier = Modifier.weight(1f)
                    .bounceClick(),
                text = "입고하기",
                contentColor = UnboxingColor.Primary40,
                containerColor = UnboxingColor.Primary90
            ) {
                navigateToInputReceiving()
            }
            
            Spacer(modifier = Modifier.width(10.dp))

            LargeButton(
                modifier = Modifier.weight(1f)
                    .bounceClick(),
                text = "출고하기"
            ) {
                navigateToInputReleasing()
            }
        }
    }
}