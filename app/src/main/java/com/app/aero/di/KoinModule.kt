package com.app.aero.di

import com.app.aero.app.ConnectivityObserver
import com.app.aero.app.ConnectivityViewModel
import com.app.aero.app.NetworkConnectivityObserver
import com.app.aero.data.network.WebSocketClient
import com.app.aero.data.repository.FeedRepositoryImpl
import com.app.aero.domain.repo.FeedRepository
import com.app.aero.presentation.feature_feed.FeedViewModel
import com.app.aero.presentation.feature_feed_details.FeedDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Duration.Companion.milliseconds

val koinAppModule = module {
    single {
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 15_000.milliseconds
            }
        }
    }

    single { WebSocketClient(get()) }

    single { FeedRepositoryImpl(get()) } bind FeedRepository::class

    viewModel { FeedViewModel(get()) }
    viewModel { FeedDetailViewModel(get()) }

    single { NetworkConnectivityObserver(get()) } bind ConnectivityObserver::class
    single { ConnectivityViewModel(get()) }
}