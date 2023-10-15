package com.example.hicompose.compose

import android.util.Log
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class Screen2(val route: String) {
    object Home : Screen2("home")
    object Detail : Screen2("detail/{id}/{name}") {
        //        fun passId(id: Int): String{
//            return "detail/${id}"
//        }
        fun passNameAndId(id: Int, name: String): String {
            return "detail/${id}/${name}"
        }
    }
}

@Composable
fun HomeScreen2(navController: NavController) {
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
                navController.navigate(Screen2.Detail.passNameAndId(2, "jerry"))
            }) {
                Text("Go to Details")
            }
        }

    }
}

@Composable
fun DetailScreen2(navController: NavController) {
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
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
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
fun NavigationWithParams() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen2.Home.route) {
            HomeScreen2(navController)
        }
        composable(Screen2.Detail.route,
            arguments = listOf(navArgument("id") {
            type = NavType.IntType }, navArgument("name") {
                type = NavType.StringType }
            )) {
            Log.d("Args", it.arguments?.getInt("id").toString())
            Log.d("Args", it.arguments?.getString("name").toString())
            DetailScreen2(navController)
        }
    }
}