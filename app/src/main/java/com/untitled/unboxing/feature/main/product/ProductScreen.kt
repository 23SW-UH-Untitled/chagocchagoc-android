package com.untitled.unboxing.feature.main.product

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.untitled.chagocchagoc.R
import com.untitled.unboxing.ui.theme.UnboxingColor
import com.untitled.unboxing.ui.theme.UnboxingTypo

@Composable
internal fun ProductScreen() {

    val scrollState = rememberLazyGridState()
    var scrollUpState by remember { mutableStateOf(false) }
    var lastScrolledIndex by remember { mutableIntStateOf(0) }

    val updateScrollPosition = remember {
        { newScrollIndex: Int ->
            if (newScrollIndex != lastScrolledIndex) {
                scrollUpState = newScrollIndex > lastScrolledIndex
                lastScrolledIndex = newScrollIndex
            }
        }
    }

    updateScrollPosition(scrollState.firstVisibleItemIndex)

    val position by animateFloatAsState(if (scrollUpState) -300f else 0f, label = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .statusBarsPadding()
    ) {
        LazyVerticalGrid(
            state = scrollState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            columns = GridCells.Fixed(2),
            content = {
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item(span = { GridItemSpan(2) }) {
                    Column {
                        Text(
                            text = "제품",
                            fontWeight = FontWeight.Bold,
                            style = UnboxingTypo.h4.copy(
                                fontSize = 26.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = UnboxingColor.Neutral95,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                                .clickable { /* TODO */ },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                tint = UnboxingColor.Neutral40,
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_magnifyingglass),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "제품을 검색해보세요",
                                style = UnboxingTypo.body2,
                                color = UnboxingColor.Neutral50
                            )
                        }
                    }
                }
                items(15) {
                    ProductItem(inventory = "3,000개", productName = "하이드 골지 오버 후드 니트 (3C)")
                }
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { translationY = position }
                .background(Color.White)
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = UnboxingColor.Neutral60,
                        painter = painterResource(id = R.drawable.ic_magnifyingglass),
                        contentDescription = null,
                    )
                }

                Spacer(modifier = Modifier.width(25.dp))

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = UnboxingColor.Neutral60,
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
private fun ProductItem(
    modifier: Modifier = Modifier,
    inventory: String,
    productName: String,
) {
    Column {
        AsyncImage(
            modifier = modifier.clip(RoundedCornerShape(20.dp)),
            model = "https://shop-phinf.pstatic.net/20231005_78/1696502208560g5UHd_JPEG/8184287702687831_682110038.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = inventory,
            style = UnboxingTypo.body2.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = UnboxingColor.Neutral20
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = productName,
            style = UnboxingTypo.body2.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 20.sp
            ),
            color = UnboxingColor.Neutral30
        )
    }
}