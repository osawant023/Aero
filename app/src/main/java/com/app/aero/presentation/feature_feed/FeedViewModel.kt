package com.app.aero.presentation.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.aero.domain.model.DtoStock
import com.app.aero.domain.repo.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: FeedRepository): ViewModel() {

    private val allStocks = mutableListOf<DtoStock>()
    private val _state = MutableStateFlow(FeedState())
    val state: StateFlow<FeedState> = _state

    init {
        loadStocks()
    }

    fun process(intent: FeedIntent) {
        when (intent) {
            FeedIntent.LoadData -> loadStocks()
            is FeedIntent.UpdateQuery -> filterStocks(intent.query)
        }
    }
    private fun filterStocks(query: String) {
        val filtered = if (query.isBlank()) {
            _state.value.stocks
        } else {
            _state.value.stocks.filter {
                it.symbol.contains(query, ignoreCase = true)
            }
        }

        _state.update {
            it.copy(
                query = query,
                stocks = filtered
            )
        }
    }
    private fun loadStocks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val data = repository.getStocks()
            allStocks.clear()
            allStocks.addAll(data)
            _state.update {
                it.copy(
                    isLoading = false,
                    stocks = data
                )
            }
        }
    }
}