package com.example.task

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Connect,
        BottomNavItem.Questions,
        BottomNavItem.Tools,
        BottomNavItem.Profile
    )

    var selectedItem by remember { mutableStateOf(0) } // لحفظ العنصر المختار

    BottomNavigation(
        backgroundColor = Color(0xFFF8F8F8), // لون خلفية شريط التنقل
        contentColor = Color.Gray,
        modifier = Modifier.border(
            width = 2.dp,
            color = Color(0xFFB280FF),
            shape = RoundedCornerShape(16.dp)
        ) // إضافة إطار بنفسجي مستدير الحواف
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (selectedItem == index) Color(0xFF00B3A6) else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (selectedItem == index) Color(0xFF00B3A6) else Color.Gray,
                        fontSize = 8.sp // حجم الخط للنصوص
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                selectedContentColor = Color(0xFF00B3A6), // اللون التوركوازي للعنصر المختار
                unselectedContentColor = Color.Gray
            )
        }
    }
}
