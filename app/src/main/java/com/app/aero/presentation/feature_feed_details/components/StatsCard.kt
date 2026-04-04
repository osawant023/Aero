package com.app.aero.presentation.feature_feed_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.core.ui.components.AppCard
import com.app.aero.domain.model.DtoStats
import com.app.aero.domain.model.DtoStockDetails

@Composable
fun StatsCard(data: DtoStockDetails) {

    AppCard {
        Column(Modifier.padding(16.dp)) {
            StatsGrid(data.stats)
        }
    }
}

@Composable
fun StatsGrid(stats: DtoStats) {

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            StatItem("OPEN", stats.open)
            StatItem("PREV. CLOSE", stats.prevClose)
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            StatItem("HIGH", stats.high, positive = true)
            StatItem("LOW", stats.low, negative = true)
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            StatItem("VOLUME", stats.volume)
            StatItem("AVG. PRICE", stats.avgPrice)
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    positive: Boolean = false,
    negative: Boolean = false
) {
    val color = when {
        positive -> Color(0xFF188038)
        negative -> Color.Red
        else -> Color.Black
    }

    Column {
        Text(label, fontSize = 10.sp, color = Color.Gray)
        Text(value, color = color, fontWeight = FontWeight.Medium)
    }
}