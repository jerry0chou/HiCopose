package com.example.hicompose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hicompose.R
import com.example.hicompose.ui.theme.HiComposeTheme

data class Message(val author: String, val body: String)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun MessageCard(msg: Message){

    Row(modifier = Modifier.height(60.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.google_icon),
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier, verticalArrangement = Arrangement.Center){
            Text(text = msg.author, style = TextStyle(fontSize =  20.sp, color = Color(0xFF201F20), fontWeight = FontWeight.Bold))
            Text(text = msg.body,  style = TextStyle(fontSize =  14.sp, color = Color.Gray))
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    Column {
        Greeting("Android")
        MessageCard(msg = Message("Jerry Chou", "A frontend developer"))
        MessageCard(msg = Message("Jack Ma", "A frontend developer"))
        MessageCard(msg = Message("Christof Norlan", "A front-end developer"))
        MessageCard(msg = Message("Jerry Chou", "A frontend developer"))
    }
}