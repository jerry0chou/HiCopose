package com.example.hicompose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hicompose.R

@Preview
@Composable
fun CircularImage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .border(
                        width = 10.dp,
                        color = Color.Magenta,
                        shape = RoundedCornerShape(55.dp)
                    ) // Adjust the shape here to CircleShape
                    .clip(RoundedCornerShape(55.dp)),
                painter = painterResource(id = R.drawable.scenery),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .border(
                        width = 10.dp,
                        color = Color.Cyan,
                        shape = CircleShape
                    ) // Adjust the shape here to CircleShape
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.scenery),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

    }
}
