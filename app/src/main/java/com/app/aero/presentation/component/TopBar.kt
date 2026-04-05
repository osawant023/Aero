package com.app.aero.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.R
import com.app.aero.core.ui.components.AppButton
import com.app.aero.data.network.WebSocketController

@Composable
fun TopBar(
    isBackArrowVisible: Boolean = false,
    onBackArrowClick: () -> Unit = {},
    onStart: () -> Unit = {},
    onStop: () -> Unit = {}
) {
    val isConnectionAlive by WebSocketController.socketConnection.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isBackArrowVisible) Icon(
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onBackArrowClick.invoke()
                    },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.icon_chart_pipes),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier.width(40.dp),
                painter = painterResource(R.drawable.icon_aero),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        StatusBadge(isConnectionAlive)

        Spacer(modifier = Modifier.width(8.dp))

        val text = if (isConnectionAlive) "Stop" else "Start"
        AppButton(text, positive = !isConnectionAlive, Modifier.clickable {
            if (isConnectionAlive) onStop.invoke() else onStart.invoke()
        })
    }
}

@Composable
fun StatusBadge(isConnectionAlive: Boolean) {
    Row(
        modifier = Modifier
            .background(Color(0xFFE6F4EA), RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(Color.Green, CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            if (isConnectionAlive) "CONNECTED" else "DISCONNECTED",
            fontSize = 12.sp,
            color = if (isConnectionAlive) Color(0xFF1E8E3E) else MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Medium
        )
    }
}
