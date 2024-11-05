package com.example.task




sealed class BottomNavItem(val title: String, val icon: Int, val route: String) {
    object Home : BottomNavItem("Accueil", R.drawable.ic_home, "home")
    object Connect : BottomNavItem("Connect", R.drawable.ic_connect, "connect")
    object Questions : BottomNavItem("Questions", R.drawable.ic_questions, "questions")
    object Tools : BottomNavItem("Tools", R.drawable.ic_tools, "tools")
    object Profile : BottomNavItem("Profile", R.drawable.ic_profile, "profile")
}
