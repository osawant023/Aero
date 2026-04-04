package com.app.aero.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.app.aero.R
import com.app.aero.app.LocalNavController
import com.app.aero.core.navigation.Graph
import com.app.aero.core.navigation.Route
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
fun UiSplashScreen(){
    val navController = LocalNavController.current
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Graph.Market){
            popUpTo(Route.SplashScreen){
                inclusive = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            Icon(
                modifier = Modifier
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
                    ,
                painter = painterResource(id = R.drawable.icon_aero),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "The Professional Trading Interface",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light,
                    lineHeight = TextUnit(20f, type = TextUnitType.Sp),
                    letterSpacing = TextUnit(2.1f, type = TextUnitType.Sp)
                )
            )
        }
    }
}