package com.app.aero.domain.repo

import com.app.aero.domain.model.DtoStock

interface FeedRepository {
    fun getStocks(): List<DtoStock>
}
