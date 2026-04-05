package com.app.aero.di

import com.app.aero.data.network.StockWebSocketManager
import com.app.aero.data.repository.FeedRepositoryImpl
import com.app.aero.domain.repo.FeedRepository
import com.app.aero.presentation.feature_feed.FeedViewModel
import com.app.aero.presentation.feature_feed_details.FeedDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import org.koin.dsl.module
import org.koin.plugin.module.dsl.bind
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel
import kotlin.time.Duration.Companion.milliseconds

val koinAppModule = module {

    single {
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 15_000.milliseconds
            }
        }
    }

    single<StockWebSocketManager>()
    single<FeedRepositoryImpl>().bind(FeedRepository::class)
    viewModel<FeedViewModel>()
    viewModel<FeedDetailViewModel>()
}