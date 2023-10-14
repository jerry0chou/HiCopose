package com.example.hicompose.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@Composable
fun Circulator(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = Color.Gray.copy(alpha = 0.2f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = Color.Magenta,
    foregroundIndicatorStrokeWidth: Float = 100f,
    bigTextFontSize: TextUnit = 28.sp,
    bigTextColor: Color = Color.Blue,
    bigTextSuffix: String = "GB",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = 24.sp,
    smallTextColor: Color = Color.Blue.copy(alpha = 0.4f)
) {
    var allowedIndicatorValue by remember { mutableStateOf(maxIndicatorValue) }
    allowedIndicatorValue =
        if (indicatorValue <= maxIndicatorValue) indicatorValue else maxIndicatorValue

    var animatedIndicatorValue by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = (allowedIndicatorValue.toFloat())
    }

    val percent = (animatedIndicatorValue / maxIndicatorValue) * 100
    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percent).toFloat(),
        animationSpec = tween(1000), label = "sweepAngle"
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000), label = "receivedValue"
    )

    val animateBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0) Color.Blue.copy(alpha = 0.4f)
        else bigTextColor,

        animationSpec = tween(1000),
        label = "animateBigTextColor"

    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )

                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )

            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmbeddedElement(
            bigText = receivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animateBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize
        )
    }

}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Round),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Round),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElement(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = smallText,
            color = smallTextColor,
            fontSize = smallTextFontSize,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${bigText} ${bigTextSuffix.take(2)}",
            color = bigTextColor,
            fontSize = bigTextFontSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CircularPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var number by remember {
            mutableStateOf(0)
        }
        Column {
            Circulator(indicatorValue = number)
            TextField(
                value = number.toString(),
                onValueChange = {
                    number =
                        if (it.isNotEmpty() && it.isDigitsOnly()) it.toInt() else 0
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

    }
}