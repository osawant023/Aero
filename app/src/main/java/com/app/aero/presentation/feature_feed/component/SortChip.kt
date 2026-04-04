package com.app.aero.presentation.feature_feed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SortChip(text: String, selected: Boolean) {
    val bg = if (selected) Color(0xFFE8F0FE) else Color(0xFFF1F3F4)
    val color = if (selected) Color(0xFF1967D2) else Color.DarkGray

    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = color, fontSize = 13.sp)
    }
}