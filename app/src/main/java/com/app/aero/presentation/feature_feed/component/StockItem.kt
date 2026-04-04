package com.app.aero.presentation.feature_feed.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.domain.model.DtoStock

@Composable
fun StockItem(stock: DtoStock, onClick : (String) -> Unit) {
    val changeColor = if (stock.isPositive) Color(0xFF188038) else Color(0xFFD93025)

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable { onClick(stock.symbol) },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(stock.symbol, fontWeight = FontWeight.Bold)
                Text(stock.exchange, fontSize = 12.sp, color = Color.Gray)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(stock.price, fontWeight = FontWeight.Medium)
                Text(stock.change, color = changeColor, fontSize = 12.sp)
            }
        }

        HorizontalDivider(color = Color(0xFFF1F3F4))
    }
}