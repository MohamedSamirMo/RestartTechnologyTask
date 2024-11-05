package com.example.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
    var showTooltip by remember { mutableStateOf(true) }
    var tooltipVisible by remember { mutableStateOf(true) }

    // Coroutine to dismiss the tooltip after 3 seconds
    LaunchedEffect(Unit) {
        delay(3000)
        showTooltip = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Home",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF26A69A),

            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Row for "Hi User Name" with notification icon on the right side
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Arrange text and icon on opposite sides
        ) {
            Text(
                text = "Hi User Name",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp) // Space between text and edge
            )

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                tint = Color.Gray, // Set color to gray as in the design
                modifier = Modifier.size(24.dp) // Size adjustment
            )
        }

        Text(
            text = "Study Plan",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Study Plan Steps
        StudyPlanStep(stepNumber = 1, title = "what is examate", isActive = true, isLastStep = false)
        StudyPlanStep(stepNumber = 2, title = "what is TCF", isActive = false, isLastStep = false)
        StudyPlanStep(stepNumber = 3, title = "Writing Tasks", isActive = false, isLastStep = true)
    }
    if (showTooltip) {
        Tooltip(text = "Vous trouverez ici votre plan d'étude", alignment = Alignment.BottomStart,
            onDismissRequest = { showTooltip = false },offset = IntOffset(0, -50)
        ) }
    // Tooltip Popup
    if (showTooltip) {
        TooltipHome(
            text = "Welcome to: how to use and enjoy",
            onDismissRequest = { showTooltip = false }
        )
    }
}

@Composable
fun StudyPlanStep(stepNumber: Int, title: String, isActive: Boolean, isLastStep: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            // الدائرة الخارجية الكبيرة التي تحتوي على القفل
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(2.dp)
                    .size(72.dp) // حجم الدائرة الخارجية الكبيرة
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = if (isActive) Color(0xFF26A69A) else Color(0xFFB0BEC5), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // قفل يظهر فقط إذا كانت الخطوة غير نشطة
                    if (!isActive) {
                        Text(
                            text = "🔒",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // دائرة داخلية صغيرة تحتوي على الرقم
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp) // حجم الدائرة الداخلية أقل من الخارجية
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = stepNumber.toString(),
                        color = if (isActive) Color(0xFF26A69A) else Color(0xFFB0BEC5),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Step title
            Text(
                text = "Unite $stepNumber: $title"
                ,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isActive) Color(0xFF26A69A) else Color.Gray
            )
        }

        //Vous trouverez ici votre plan d'étude
        // رسم السهم العمودي إذا لم تكن الخطوة الأخيرة
        if (!isLastStep) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                // الخط العمودي بين الخطوات
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(54.dp)
                        .background(Color(0xFF26A69A))
                )

//                // السهم المتجه للأسفل
//                Text(
//                    text = "↓",
//                    color = Color.Gray,
//                    fontSize = 20.sp
//                )
            }
        }
    }
}

@Composable
fun TooltipHome(text: String, onDismissRequest: () -> Unit) {
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismissRequest
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF100E0E).copy(alpha = 0.9f), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = text,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
                Text(
                    text = "\n Examate",
                    color = Color.Cyan,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                Text(
                    text = "\nTap anywhere on the screen to continue",
                    color = Color.Cyan,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
    }
}
