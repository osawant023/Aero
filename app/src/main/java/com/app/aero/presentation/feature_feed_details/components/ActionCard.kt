package com.app.aero.presentation.feature_feed_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.aero.core.ui.components.AppButton
import com.app.aero.core.ui.components.AppCard
import com.app.aero.domain.model.DtoStockDetails

@Composable
fun ActionCard(data: DtoStockDetails) {

    AppCard {
        Column(Modifier.padding(16.dp)) {

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {


                AppButton(
                    text = "BUY",
                    modifier = Modifier.weight(1f),
                    positive = true,
                )
                AppButton(
                    text = "SELL",
                    modifier = Modifier.weight(1f),
                    positive = false
                )
//                Button(
//                    onClick = {},
//                    modifier = Modifier.weight(1f),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E5BB8))
//                ) {
//                    Text("BUY")
//                }
//
//                Button(
//                    onClick = {},
//                    modifier = Modifier.weight(1f),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD93025))
//                ) {
//                    Text("SELL")
//                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Available Funds", color = Color.Gray)
                Text(data.funds, fontWeight = FontWeight.Bold)
            }
        }
    }
}