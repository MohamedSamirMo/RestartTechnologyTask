package com.example.task

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavController
import kotlinx.coroutines.delay

data class Category(
    val title: String,
    val questionCount: Int,
    val progress: Int
)

@Composable
fun QuestionsScreen(navController: NavController) {
    var tooltipVisible by remember { mutableStateOf(true) }
    var filterTooltipVisible by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Oral") } // Default to "Oral"

    // Coroutine to dismiss the tooltip after 3 seconds
    LaunchedEffect(Unit) {
        delay(3000)
        tooltipVisible = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F5F8)) // لون خلفية أكثر أناقة
            .padding(16.dp)
    ) {
        // Main title
        Text(
            text = "Questions",
            fontSize = 26.sp,
            color = Color(0xFF00695C), // لون أغمق للعنوان
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Category sections with icons below the title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Writing Category
            CategoryItem(
                icon = Icons.Filled.Edit,
                label = "Writing",
                isSelected = selectedCategory == "Writing",
                onClick = { selectedCategory = "Writing" }
            )

            // Oral Category
            CategoryItem(
                icon = Icons.Filled.Call,
                label = "Oral",
                isSelected = selectedCategory == "Oral",
                onClick = { selectedCategory = "Oral" }
            )
        }

        // Filter button with icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                modifier = Modifier
                    .clickable { filterTooltipVisible = true }
                    .background(Color(0xFF00695C), RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Filter Icon",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Filter",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Tooltip for Filter button if activated
        if (filterTooltipVisible) {
            TooltipFilter(
                text = "Vous pouvez filtrer pour voir un type exact de questions",
                alignment = Alignment.TopEnd,
                onDismissRequest = { filterTooltipVisible = false },
                offset = IntOffset(80, 40)
            )
        }

        // Content based on selected category
        when (selectedCategory) {
            "Oral" -> {
                // Display questions list for Oral category
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(10) { index ->
                        QuestionItem()
                    }
                }
            }
            "Writing" -> {
                // Display custom grid view of Writing tasks
                val categories = listOf(
                    Category("Voyage", 10, 100),
                    Category("Immigration", 5, 50),
                    Category("Technologie", 5, 50),
                    Category("Art et Culture", 5, 50),
                    Category("Environment", 5, 50),
                    Category("Travel", 5, 50)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(category)
                    }
                }
            }
        }
    }

    // Tooltip at the bottom
    if (tooltipVisible) {
        TooltipQuestion(
            text = "Voici les questions avec des réponses modèles",
            alignment = Alignment.BottomCenter,
            onDismissRequest = { tooltipVisible = false },
            offset = IntOffset(0, -50)
        )
    }
}

@Composable
fun CategoryCard(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${category.questionCount} sur 10 Questions",
            fontSize = 14.sp,
            color = Color(0xFF00695C),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.title,
            fontSize = 19.sp,
            color = Color(0xFF1A1A1A),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Progress ${category.progress}%",
            fontSize = 14.sp,
            color = Color(0xFF7D7D7D)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Progress Bar
        LinearProgressIndicator(
            progress = category.progress / 100f,
            color = Color(0xFF00695C),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun CategoryItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                if (isSelected) Color(0xFF00695C) else Color.Transparent,
                shape = RoundedCornerShape(50)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$label Icon",
            tint = if (isSelected) Color.White else Color(0xFF555555),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xFF555555),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun QuestionItem() {
    var showTooltip by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clickable { showTooltip = true }
    ) {
        Column {
            Text(text = "Technology", color = Color(0xFF888888), fontSize = 14.sp)
            Text(text = "Task 3", fontSize = 22.sp, color = Color(0xFF333333), fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Quand quelqu'un quitte son pays pour aller vivre ailleurs, c'est souvent parce qu'il n'a pas d'autre choix. Qu'en pensez-vous?",
                color = Color(0xFF444444),
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Icon(imageVector = Icons.Filled.List, contentDescription = "Answers", tint = Color(0xFF888888))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "25 Answers", color = Color(0xFF888888), fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "17/09/2023", color = Color(0xFF888888), fontSize = 14.sp)
            }
        }

        if (showTooltip) {
            TooltipQuestion(
                text = "Cette question a plusieurs réponses disponibles",
                alignment = Alignment.BottomEnd,
                onDismissRequest = { showTooltip = false },
                offset = IntOffset(100, -16)
            )
        }
    }
}


@Composable
fun TooltipQuestion(
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

                // سهم التلميح
                Canvas(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(horizontalOffset.dp, 0.dp)
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
}@Composable
fun TooltipFilter(
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

                // سهم التلميح
                Canvas(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(100.dp, 0.dp)
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