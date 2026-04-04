package com.app.aero.presentation.feature_feed_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.domain.model.DtoStockDetails

@Composable
fun HeaderSection(data: DtoStockDetails) {

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(data.symbol, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(data.exchange, fontSize = 12.sp)
            }
        }

        Text(data.companyName, color = Color.Gray)

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                data.price,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(8.dp))

            Text(
                data.change,
                color = if (data.isPositive) Color(0xFF188038) else Color.Red,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            "REAL-TIME • ${data.lastUpdated}",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}