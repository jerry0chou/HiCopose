package com.example.hicompose.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

class HomeViewModel : ViewModel() {
    private var _items = mutableStateListOf<CustomData>()
    val items: List<CustomData> = _items

    init {
        getData()
    }

    private fun getData() {
        for (number in 0 until 10) {
            _items.add(
                element = CustomData(
                    id = number,
                    image = "https://www.google.com.hk/logos/doodles/2023/veterans-day-2023-6753651837109963-l.webp",
                    title = "#$number Lorem Ipsum",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    badges = listOf(
                        Icons.Default.Check,
                        Icons.Default.Edit,
                        Icons.Default.Face,
                        Icons.Default.Email,
                        Icons.Default.List,
                        Icons.Default.Home
                    )
                )
            )
        }
    }

}

data class CustomData(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val badges: List<ImageVector>
)


sealed class AdaptiveLayoutScreen(val route: String) {
    object Home : Screen("home")
}

@Composable
fun AdaptiveLayoutNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AdaptiveLayoutScreen.Home.route
    ) {
        composable(route = AdaptiveLayoutScreen.Home.route) {
            AdaptiveLayoutHomeScreen()
        }
    }
}

@Composable
fun AdaptiveLayoutHomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val items = homeViewModel.items
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items, key = {it.id}){
            CustomCard(data = it)
        }
    }
}

@Composable
fun CustomCard(data: CustomData) {
    Row(modifier = Modifier.border(width = 1.dp, color = Color.LightGray)) {
        AsyncImage(
            modifier = Modifier
                .weight(1f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = data.image)
                .crossfade(true)
                .build(),
            contentDescription = "Card Image",
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(12.dp))

        BoxWithConstraints(
            modifier = Modifier
                .weight(2f)
                .padding(vertical = 12.dp)
        ) {
            AdaptiveContent(data = data)
        }
    }
}

@Composable
fun BoxWithConstraintsScope.AdaptiveContent(data: CustomData) {
    val badgeSize = 24.dp
    val padding = 24.dp
    val numberOfBadgesToShow = maxWidth.div(badgeSize + padding).toInt().minus(1)
    val remainingBadges = data.badges.size - numberOfBadgesToShow

    Log.d("HomeScreen", "${this.maxWidth}")
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = data.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.description,
            maxLines = if (this@AdaptiveContent.maxWidth > 250.dp) 10 else 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(space = padding)) {
            data.badges.take(numberOfBadgesToShow).forEach {
                Icon(
                    modifier = Modifier.size(badgeSize),
                    imageVector = it,
                    contentDescription = "Badge Icon"
                )
            }
            if (remainingBadges > 0) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .padding(6.dp)
                ) {
                    Text(
                        text = "+$remainingBadges",
                        style = TextStyle(fontSize = 20.sp)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun MainAdaptiveLayout() {
    AdaptiveLayoutNavGraph(navController = rememberNavController())
}