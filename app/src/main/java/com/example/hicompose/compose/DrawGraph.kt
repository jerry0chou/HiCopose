package com.example.hicompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@Preview
@Composable
fun DrawGraph() {
    val controller = rememberDrawController()
    controller.apply {
        changeStrokeWidth(30f)
        changeColor(Color.Magenta)
        changeOpacity(0.5f)
    }
    DrawBox(
        drawController = controller,
        bitmapCallback = { img, throwable -> println("hello box") })
}