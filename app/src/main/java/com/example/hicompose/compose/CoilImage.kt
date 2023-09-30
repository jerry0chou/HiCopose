package com.example.hicompose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
@Preview(showBackground = true)
@Composable
fun CoilImage() {
    // AndroidManifest.xml android:usesCleartextTraffic="true"
    // uses-permission  INTERNET ACCESS_NETWORK_STATE
    // https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png
    // https://avatars.githubusercontent.com/u/14994036?v=4
    val image = "https://avatars.githubusercontent.com/u/14994036?v=4"
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberAsyncImagePainter(image)
        Image(
            modifier = Modifier.size(250.dp),
            painter = painter,
            contentDescription = "Logo" // or provide appropriate content description
        )
        val painterState = painter.state
        if(painterState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }
    }
}

