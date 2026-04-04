package com.app.aero.presentation.feature_feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.aero.data.FeedRepositoryImpl
import com.app.aero.domain.model.DtoStock
import com.app.aero.presentation.component.TopBar
import com.app.aero.presentation.feature_feed.component.SearchBar
import com.app.aero.presentation.feature_feed.component.SortSection
import com.app.aero.presentation.feature_feed.component.StockItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UiFeedScreen(viewModel: FeedViewModel = koinViewModel<FeedViewModel>()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    UiFeedContent(state = state){

    }
}

@Composable
fun UiFeedContent(state: FeedState, onIntent: (FeedIntent)-> Unit) {
    Column {
        TopBar()
        SearchBar(state.query, onQueryChange = {
            onIntent(FeedIntent.UpdateQuery(it))
        })
        SortSection()
        HorizontalDivider()
        StockList(state.stocks)
    }
}

@Preview(showBackground = true)
@Composable
fun UiFeedContentPreview() {
    val state by produceState(
        initialValue = FeedState()
    ) {
        val repo = FeedRepositoryImpl()
        value = FeedState(stocks = repo.getStocks())
    }
    UiFeedContent(state = state, onIntent = {})
}



@Composable
fun StockList(stocks: List<DtoStock>) {
    LazyColumn {
        items(stocks) {
            StockItem(it)
        }
    }
}

