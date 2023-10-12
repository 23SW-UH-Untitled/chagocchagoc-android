package com.untitled.unboxing.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.untitled.unboxing.ui.theme.UnboxingColor
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
internal fun LineChart(
    data: List<Pair<String, Double>> = emptyList(),
    modifier: Modifier = Modifier
) {
    val spacing = 0f
    val graphColor = UnboxingColor.Primary40
    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }

    val upperValue = remember(data) { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0 }
    val lowerValue = remember(data) { (data.minOfOrNull { it.second }?.toInt() ?: 0) }

    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = 0xFF373C43.toInt()
            textAlign = Paint.Align.LEFT
            textSize = density.run { 12.sp.toPx() }
        }
    }
    
    Canvas(modifier = modifier) {
        val spacePerDate = (size.width - spacing) / data.size
        (data.indices).forEach { i ->
            val date = data[i].first
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    date,
                    spacing + i * spacePerDate,
                    size.height + 50,
                    textPaint
                )
            }
        }

        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            data.indices.forEach { i ->
                val info = data[i]
                val nextInfo = data.getOrNull(i + 1) ?: data.last()
                val leftRatio = (info.second - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.second - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerDate
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * spacePerDate
                val y2 = height - spacing - (rightRatio * height).toFloat()
                if (i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3 .dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }

}