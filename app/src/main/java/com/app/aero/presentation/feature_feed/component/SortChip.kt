package com.app.aero.presentation.feature_feed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SortChip(data: SortChipData, selected: Boolean, onClick: () -> Unit, onClearChipSelection: () -> Unit = {}) {
    val bg = if (selected) Color(0xFFE8F0FE) else Color(0xFFF1F3F4)
    val color = if (selected) MaterialTheme.colorScheme.primary else Color.DarkGray

    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (selected){
                val icon = if (data.sort == SortBy.ASCE)
                    Icons.Filled.ArrowDropDown
                else
                    Icons.Filled.ArrowDropUp
                Icon(imageVector = icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(data.name, color = color, fontSize = 13.sp)
            Spacer(modifier = Modifier.width(8.dp))
            if (selected){
                Icon(modifier = Modifier.size(15.dp).clickable{
                    onClearChipSelection()
                }, imageVector = Icons.Filled.Close , contentDescription = null, tint = color)
            }
        }
    }
}