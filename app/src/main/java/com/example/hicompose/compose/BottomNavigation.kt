package com.example.hicompose.compose

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}

// note: remember to pass navHostController as a parameter
@Composable
fun HomePage(navHostController: NavHostController) {
    Text(text = "HomePage")
}

// note: remember to pass navHostController as a parameter
@Composable
fun ProfilePage(navHostController: NavHostController) {
    Text(text = "ProfilePage")
}

// note: remember to pass navHostController as a parameter
@Composable
fun SettingsPage(navHostController: NavHostController) {
    Text(text = "SettingsPage")
}

@Composable
fun BottomNavGraph(navController: NavHostController, innerPaddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPaddingValues)
    ) {
        composable(route = BottomBarScreen.Home.route) { HomePage(navController) }
        composable(route = BottomBarScreen.Profile.route) { ProfilePage(navController) }
        composable(route = BottomBarScreen.Settings.route) { SettingsPage(navController) }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomBarScreen.Home, BottomBarScreen.Profile, BottomBarScreen.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color.Magenta.copy(alpha = 0.4f), modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    )) {

        items.forEach { screen ->
            val selected = screen.route == currentRoute
            NavigationBarItem(
                selected = selected,
                onClick = {
                    Log.d("Navigation", screen.title)
                    Log.d("Navigation", screen.route)
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to avoid building up a stack of destinations
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }

                },
                label = {
                    Text(
                        text = screen.title,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "${screen.title} Icon",
                    )
                })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // BottomNavigationBar and BottomNavGraph should have the same navController
    // then pass this object to its children route
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        BottomNavGraph(navController, innerPadding)
    }
}