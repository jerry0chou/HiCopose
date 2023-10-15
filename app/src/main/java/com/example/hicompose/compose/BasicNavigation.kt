package com.example.hicompose.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Home",
                color = Color.Magenta,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                navController.navigate(Screen.Detail.route)
            }) {
                Text("Go to Details")
            }
        }

    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Detail",
                color = Color.Magenta,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
//                navController.navigate(Screen.Home.route)
               navController.navigate(Screen.Home.route){
                   popUpTo(Screen.Home.route){
                       inclusive = true
                   }
               } // return back using system back button and avoid cyclic operation
            }) {
                Text("Go to Home")
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BasicNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Detail.route) {
            DetailScreen(navController)
        }
    }
}