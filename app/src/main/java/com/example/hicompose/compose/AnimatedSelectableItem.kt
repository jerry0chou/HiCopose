package com.example.hicompose.compose

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val primaryColor = Color.Magenta
val greyColor = Color.DarkGray

fun colorSelection(selected: Boolean, selectedColor: Color, unselectedColor: Color): Color =
    if (selected) selectedColor else unselectedColor.copy(alpha = 0.25f)


@Composable
fun SelectedItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    titleColor: Color = if (selected) primaryColor else greyColor,
    titleSize: TextUnit = 26.sp,
    titleWeight: FontWeight = FontWeight.Medium,
    subtitle: String? = null,
    subtitleColor: Color = colorSelection(selected, greyColor, greyColor),
    borderWidth: Dp = 1.dp,
    borderColor: Color = colorSelection(selected, primaryColor, greyColor),
    borderShape: Shape = RoundedCornerShape(size = 10.dp),
    icon: ImageVector = Icons.Default.CheckCircle,
    iconColor: Color = colorSelection(selected, primaryColor, greyColor),
    onClick: () -> Unit
) {
//    val color = remember { Animatable(0f) }
    val scaleA = remember {
        Animatable(initialValue = 1f)
    }
    val scaleB = remember {
        Animatable(initialValue = 1f)
    }
    val clickEnabled = remember { mutableStateOf(true) }


    LaunchedEffect(key1 = selected) {
        if (selected) {
            launch {
                val jobA = launch {
                    scaleA.animateTo(
                        targetValue = 0.3f,
                        animationSpec = tween(
                            durationMillis = 50
                        )
                    )
                    scaleA.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                }
                val jobB = launch {
                    scaleB.animateTo(
                        targetValue = 0.9f,
                        animationSpec = tween(
                            durationMillis = 50
                        )
                    )
                    scaleB.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                }

                jobA.join()
                jobB.join()
                clickEnabled.value = true
            }
        }
    }
    Column(
        modifier = modifier
            .scale(scale = scaleB.value)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = borderShape
            )
            .clip(borderShape)
            .clickable(enabled = clickEnabled.value) {
                Log.d("SelectedItem", "inner click $selected")
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(8f),
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontWeight = titleWeight,
                    fontSize = titleSize
                )
            )
            IconButton(
                modifier = Modifier
                    .weight(2f)
                    .scale(scale = scaleA.value),
                onClick = {
                    if (clickEnabled.value) {
                        onClick()
                    }
                }) {
                Icon(imageVector = icon, contentDescription = "Selectable Item", tint = iconColor)
            }
        }

        if (subtitle != null) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                text = subtitle,
                style = TextStyle(color = subtitleColor, fontSize = 20.sp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
fun MainAnimatedSelectedItem() {
    var selected by remember {
        mutableStateOf(false)
    }
    var selected2 by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectedItem(selected = selected, title = "Jerry Chou") {
                selected = !selected
            }

            SelectedItem(
                selected = selected2,
                title = "Hello world",
                subtitle = "some random text, some random textsome random textsome random textsome random text"
            ) {
                selected2 = !selected2
            }
        }
    }
}