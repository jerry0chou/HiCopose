package com.example.hicompose.compose

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hicompose.R
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox



@Composable
fun OrdinaryItem(title: String, desc: String, drawableImageId: Int) {
    Row(
        modifier = Modifier
//            .border(
//                width = 1.dp, color = Color(0xFFFFC603),
//                shape = RoundedCornerShape(18.dp)
//            )
//            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF0BF0E))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Image(
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 1.dp,
                    color = Color.Green.copy(0.5f),
                    shape = CircleShape
                ) // Adjust the shape here to CircleShape
                .clip(CircleShape),
            painter = painterResource(id = drawableImageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(7f)) {
            Text(text = title, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold))
            Text(
                text = desc,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

        }
    }
}

// website https://github.com/saket/swipe
@Composable
fun SwipeFeature(block: @Composable ()-> Unit) {

    val archive = SwipeAction(
        icon = rememberVectorPainter(Icons.Filled.Settings),
        background = Color.Green,
        onSwipe = { Log.d("SwipeFeature", "archive") },
    )

    val snooze = SwipeAction(
        icon = rememberVectorPainter(Icons.Filled.Star),
        background = Color.Yellow,
        isUndo = true,
        onSwipe = { Log.d("SwipeFeature", "snooze") },

        )

    SwipeableActionsBox(
        startActions = listOf(archive),
        endActions = listOf(snooze)
    ) {
        // Swipeable content goes here.
        block()
    }
}
@Preview
@Composable
fun SwipeList() {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        SwipeFeature {
            OrdinaryItem(
                "Steven Liu",
                "Hello, I am humorous comedian, I wish my jokes would entertain you",
                R.drawable.scenery
            )
        }
        SwipeFeature {
            OrdinaryItem(
                "Jerry Chou",
                "I am a very compassionate android developer",
                R.drawable.cane
            )
        }
    }
}