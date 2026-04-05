package com.app.aero.presentation.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.aero.domain.model.DtoStockDetails
import com.app.aero.domain.repo.FeedRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: FeedRepository) : ViewModel() {

    private val allStocks = mutableListOf<DtoStockDetails>()
    private val _state = MutableStateFlow(FeedState())
    val state: StateFlow<FeedState> = _state

    init {
        loadStocks()
    }

    private val _navigateTo = MutableSharedFlow<Pair<String, String>>()
    val navigateTo: SharedFlow<Pair<String, String>> = _navigateTo
    fun process(intent: FeedIntent) {
        when (intent) {
            FeedIntent.LoadData -> loadStocks()
            is FeedIntent.UpdateQuery -> filterStocks(intent.query)
            is FeedIntent.NavigateToFeedDetails -> {
                viewModelScope.launch {
                    _navigateTo.emit(Pair("details", intent.symbol))
                }
            }
            is FeedIntent.StartConnection -> repository.start()
            is FeedIntent.StopConnection -> repository.stop()
        }
    }

    private fun filterStocks(query: String) {
        val filtered = if (query.isBlank()) {
            allStocks
        } else {
            allStocks.filter {
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
            repository.observeStocks().collect {
                val data = it
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
}