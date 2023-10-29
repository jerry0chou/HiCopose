package com.example.hicompose.compose

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hicompose.ui.theme.Purple40
import kotlinx.coroutines.delay

sealed class SplashScreen(val route: String) {
    object Splash : SplashScreen("Splash")
    object Home : SplashScreen("Home")
}

@Composable
fun AnimatedSplashScreen(navHostController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000), label = ""
    )
    LaunchedEffect(key1 = startAnimation){
        startAnimation = true
        delay(3000)
        navHostController.popBackStack()
        navHostController.navigate(Screen.Home.route)
    }
    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple40)
            .fillMaxSize().alpha(alpha),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector = Icons.Default.Email, contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}

@Composable
fun configNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = SplashScreen.Splash.route
    ) {
        composable(route = SplashScreen.Splash.route) {
            AnimatedSplashScreen(navHostController)
        }
        composable(route = SplashScreen.Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}


@Preview
@Composable
fun mainAnimatedSplash() {
    val navHostController = rememberNavController()
    configNavGraph(navHostController = navHostController)
}
