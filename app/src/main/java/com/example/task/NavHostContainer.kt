package com.example.task



import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavHostContainer(navController: NavHostController, paddingValues: Dp) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route

    ) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Connect.route) { ConnectScreen() }
        composable(BottomNavItem.Questions.route) { QuestionsScreen(
            navController = navController
        ) }
        composable(BottomNavItem.Tools.route) { ToolsScreen() }
        composable(BottomNavItem.Profile.route) { ProfileScreen() }
    }
}
