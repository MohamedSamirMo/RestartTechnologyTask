package com.example.task

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay

@Composable
fun ConnectScreen() {
    var selectedTab by remember { mutableStateOf("Suggestions") }
    var showTooltip by remember { mutableStateOf(true) }

    // Coroutine to dismiss the tooltip after 3 seconds
    LaunchedEffect(Unit) {
        delay(3000)
        showTooltip = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with "Connect" title and filter icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Connect",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF26A69A),
                modifier = Modifier.weight(1f)
            )
        }

        // Tabs for "Suggestions" and "Chat"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF818382), RoundedCornerShape(16.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabItem(
                title = "Suggestions",
                isSelected = selectedTab == "Suggestions",
                onClick = { selectedTab = "Suggestions" }
            )
            TabItem(
                title = "Chat",
                isSelected = selectedTab == "Chat",
                onClick = { selectedTab = "Chat" }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == "Suggestions") {
            // Suggested Study Partners Header with Icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Suggested Study Partners:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 1.dp)
                )
                Spacer(modifier = Modifier.width(70.dp)) // Space between the text and the icon
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Filter",
                    tint = Color(0xFF26A69A),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            // Handle filter icon click action if needed
                        }
                )
            }

            // List of study partners
            StudyPartnerCard(name = "Reem Sayed", languageLevels = listOf("English", "Arabic", "French"))
            StudyPartnerCard(name = "Sara Khaled", languageLevels = listOf("English", "Arabic", "French"))
            StudyPartnerCard(name = "Ali Omar", languageLevels = listOf("English", "Arabic", "French"))
        } else {
            // Chat screen placeholder
            Text(
                text = "Chat Feature Coming Soon!",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }

    // Tooltip at the bottom
    if (showTooltip) {
        TooltipConnect(
            text = "Vous trouverez ici des partenaires d'étude ou des personnes avec qui vous connecter",
            alignment = Alignment.BottomCenter,
            onDismissRequest = { showTooltip = false },
            offset = IntOffset(0, -50)
        )
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = if (isSelected) Color.White else Color(0xFF80CBC4),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(if (isSelected) Color(0xFF004D40) else Color.Transparent, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun StudyPartnerCard(name: String, languageLevels: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circle with initials
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = Color(0xFF26A69A), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.split(" ").map { it.first() }.joinToString(""),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Partner information with filter icon
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(13.dp))
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Filter",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "Targeting B1",
                    fontSize = 14.sp,
                    color = Color(0xFF26A69A)
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    for (language in languageLevels) {
                        Text(
                            text = language,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TooltipConnect(
    text: String,
    alignment: Alignment,
    onDismissRequest: () -> Unit,
    offset: IntOffset,
    horizontalOffset: Float = 0f
) {
    Popup(
        alignment = alignment,
        onDismissRequest = onDismissRequest
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .offset { offset }
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF212121).copy(alpha = 0.9f), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Canvas(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(-70.dp, 0.dp)
                ) {
                    val path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width / 2, size.height)
                        close()
                    }
                    drawPath(path, color = Color(0xFF212121).copy(alpha = 0.9f), style = Fill)
                }
            }
        }
    }
}
