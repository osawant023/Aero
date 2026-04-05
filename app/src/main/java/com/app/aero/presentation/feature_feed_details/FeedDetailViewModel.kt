package com.app.aero.presentation.feature_feed_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.aero.domain.repo.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedDetailViewModel(private val repository: FeedRepository) : ViewModel() {

    private val _state = MutableStateFlow(FeedDetailState())
    val state: StateFlow<FeedDetailState> = _state


    fun process(intent: FeedDetailIntent) {
        when (intent) {
            is FeedDetailIntent.Load -> load(intent.symbol)
            is FeedDetailIntent.StartConnection -> repository.start()
            is FeedDetailIntent.StopConnection -> repository.stop()
            else -> {}
        }
    }

    private fun load(symbol: String) {
        viewModelScope.launch {
            repository.observeStock(symbol)
                .collect { model ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            data = model
                        )
                    }
                }
        }
    }

}