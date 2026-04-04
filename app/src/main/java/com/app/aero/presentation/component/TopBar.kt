package com.app.aero.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.R

@Composable
fun TopBar(
    isBackArrowVisible: Boolean = false,
    onBackArrowClick: () -> Unit = {}
) {
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

        StatusBadge()

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text("START")
        }
    }
}

@Composable
fun StatusBadge() {
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
            "CONNECTED",
            fontSize = 12.sp,
            color = Color(0xFF1E8E3E),
            fontWeight = FontWeight.Medium
        )
    }
}
