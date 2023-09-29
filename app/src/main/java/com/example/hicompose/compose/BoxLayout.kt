package com.example.hicompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun BoxLayout(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
        ){
        Box(modifier = Modifier
            .background(Color.Red)
            .width(100.dp)
            .height(100.dp)
            .verticalScroll(rememberScrollState())
        ){
            Text(text = "I love android!", fontSize = 30.sp)
        }
    }

}

