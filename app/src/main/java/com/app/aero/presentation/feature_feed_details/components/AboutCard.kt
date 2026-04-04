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
import androidx.compose.ui.unit.sp
import com.app.aero.core.ui.components.AppCard
import com.app.aero.domain.model.DtoStockDetails

@Composable
fun AboutCard(data: DtoStockDetails) {
    AppCard {
        Column(Modifier.padding(16.dp)) {

            Text("ABOUT ${data.companyName.uppercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(data.about, fontSize = 14.sp)

            Spacer(Modifier.height(16.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                InfoItem("CEO", data.ceo)
                InfoItem("FOUNDED", data.founded)
                InfoItem("HQ", data.headquarters)
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(label, fontSize = 10.sp, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Medium)
    }
}