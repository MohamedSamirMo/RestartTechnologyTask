package com.example.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen() {
    val navController = rememberNavController() // لإنشاء نظام التنقل

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // شريط التنقل السفلي
    ) { paddingValues ->
        // NavHost لتحديد التنقل بين الشاشات المختلفة
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route, // شاشة البداية
            Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() } // شاشة Home
            composable(BottomNavItem.Connect.route) { ConnectScreen() } // شاشة Connect
            composable(BottomNavItem.Questions.route) { QuestionsScreen(
                navController = navController
            ) } // شاشة Questions
            composable(BottomNavItem.Tools.route) { ToolsScreen() } // شاشة Tools
            composable(BottomNavItem.Profile.route) { ProfileScreen() } // شاشة Profile
        }
    }
}
