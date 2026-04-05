package com.app.aero.presentation.feature_feed_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.aero.app.LocalNavController
import com.app.aero.core.ui.theme.AeroTheme
import com.app.aero.core.ui.theme.LocalSpacing
import com.app.aero.data.model.StockDetailsData
import com.app.aero.data.model.toDomain
import com.app.aero.data.network.mockJson
import com.app.aero.presentation.component.TopBar
import com.app.aero.presentation.feature_feed_details.components.AboutCard
import com.app.aero.presentation.feature_feed_details.components.ActionCard
import com.app.aero.presentation.feature_feed_details.components.HeaderSection
import com.app.aero.presentation.feature_feed_details.components.StatsCard
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UiFeedDetailsScreen(
    symbol: String,
    viewModel: FeedDetailViewModel = koinViewModel<FeedDetailViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    LaunchedEffect(Unit) {
        viewModel.process(FeedDetailIntent.Load(symbol))
    }
    UiFeedDetail(state) {
        when (it) {
            FeedDetailIntent.NavigateUp -> navController.navigateUp()
            else -> viewModel.process(it)
        }
    }
}


@Composable
fun UiFeedDetail(state: FeedDetailState, onIntent: (FeedDetailIntent) -> Unit) {
    val spacing = LocalSpacing.current
    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val data = state.data ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())

    ) {

        TopBar(
            isBackArrowVisible = true,
            onBackArrowClick = {
                onIntent(FeedDetailIntent.NavigateUp)
            }, onStart = {
                onIntent(FeedDetailIntent.StartConnection)
            }, onStop = {
                onIntent(FeedDetailIntent.StopConnection)
            }
        )

        Column(modifier = Modifier.padding(15.dp)) {
            HeaderSection(data)

            Spacer(Modifier.height(spacing.lg.dp))

            AboutCard(data)

            Spacer(Modifier.height(spacing.lg.dp))

            ActionCard(data)

            Spacer(Modifier.height(spacing.lg.dp))

            StatsCard(data)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiFeedDetailsContentPreview() {
    AeroTheme {
        val state by produceState(
            initialValue = FeedDetailState()
        ) {
            val mockData =
                Json.decodeFromString<List<StockDetailsData>>(mockJson).map { it.toDomain() }
            value = FeedDetailState(data = mockData.first())
        }
        UiFeedDetail(state = state) {}
    }
}