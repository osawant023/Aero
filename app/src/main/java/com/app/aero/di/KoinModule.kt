package com.app.aero.di

import com.app.aero.data.FeedRepositoryImpl
import com.app.aero.domain.repo.FeedRepository
import com.app.aero.presentation.feature_feed.FeedViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.bind
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel

val koinAppModule = module {

    single<FeedRepositoryImpl>().bind(FeedRepository::class)
    viewModel<FeedViewModel>()
}