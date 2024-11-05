package com.example.task

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Fill


@Composable
fun Tooltip(
    text: String,
    alignment: Alignment,
    onDismissRequest: () -> Unit,
    offset: IntOffset,
    horizontalOffset: Float = 0f // إزاحة أفقية للسهم
) {
    Popup(
        alignment = alignment,
        onDismissRequest = onDismissRequest // يتم استدعاؤه عند الضغط خارج التلميح لإخفائه
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .offset { offset } // تحديد الموضع باستخدام offset
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // صندوق التلميح مع الخلفية والنص
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

                // السهم الذي يشير لأسفل باستخدام Canvas
//                Spacer(modifier = Modifier.height(0.dp)) // مسافة صغيرة بين النص والسهم
                Canvas(
                    modifier = Modifier
                        .size(16.dp) // تحديد حجم السهم
                        .offset(-110.dp, 0.dp) // إزاحة أفقية للسهم
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
@Composable
@Preview
fun TooltipPreview() {
    var showTooltip by remember { mutableStateOf(true) }

    if (showTooltip) {
        Tooltip(
            text = "Vous trouverez ici votre plan d'étude",
            alignment = Alignment.TopCenter,
            onDismissRequest = { showTooltip = false },
            offset = IntOffset(0, -50) // تعديل offset لجعل التلميح يظهر فوق العنصر
        )
    }
}
