package com.example.hicompose.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hicompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GoogleButton() {
    var clicked by remember { mutableStateOf(false) }
    Surface(
        onClick = {clicked = !clicked},
        shape = RoundedCornerShape(13.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp),
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign up with Google", modifier = Modifier
            )
            if(clicked){
                Spacer(modifier = Modifier.width(8.dp))
                CircularProgressIndicator(
                    modifier = Modifier.height(16.dp).width(16.dp),
                    strokeWidth = 2.dp,
                    color = Color.Magenta
                )
            }
        }

    }
}