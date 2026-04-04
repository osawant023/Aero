package com.app.aero.data.repository

import com.app.aero.data.model.StockData
import com.app.aero.data.model.StockDetailsData
import com.app.aero.data.model.toDomain
import com.app.aero.domain.model.DtoStock
import com.app.aero.domain.model.DtoStockDetails
import com.app.aero.domain.repo.FeedRepository
import kotlinx.serialization.json.Json

class FeedRepositoryImpl : FeedRepository {

    private val json = """
[
  {
    "symbol": "NVDA",
    "exchange": "NASDAQ",
    "price": 882.12,
    "change": 14.22,
    "changePercent": 1.64
  },
  {
    "symbol": "MSFT",
    "exchange": "NASDAQ",
    "price": 420.55,
    "change": -2.15,
    "changePercent": -0.51
  },
  {
    "symbol": "GOOGL",
    "exchange": "NASDAQ",
    "price": 151.77,
    "change": 0.88,
    "changePercent": 0.58
  },
  {
    "symbol": "AAPL",
    "exchange": "NASDAQ",
    "price": 170.73,
    "change": -1.30,
    "changePercent": -0.76
  },
  {
    "symbol": "AMZN",
    "exchange": "NASDAQ",
    "price": 178.22,
    "change": 2.10,
    "changePercent": 1.19
  },
  {
    "symbol": "META",
    "exchange": "NASDAQ",
    "price": 496.24,
    "change": 3.55,
    "changePercent": 0.72
  },
  {
    "symbol": "TSLA",
    "exchange": "NASDAQ",
    "price": 163.57,
    "change": -4.12,
    "changePercent": -2.46
  },
  {
    "symbol": "AVGO",
    "exchange": "NASDAQ",
    "price": 1345.50,
    "change": 22.40,
    "changePercent": 1.69
  },
  {
    "symbol": "ASML",
    "exchange": "NASDAQ",
    "price": 954.20,
    "change": -8.30,
    "changePercent": -0.86
  },
  {
    "symbol": "COST",
    "exchange": "NASDAQ",
    "price": 725.10,
    "change": 1.45,
    "changePercent": 0.20
  }
]
    """
    private val jsonDetails = """
[
  {
    "symbol": "NVDA",
    "exchange": "NASDAQ",
    "price": 135.58,
    "change": 4.48,
    "changePercent": 3.42,

    "companyName": "NVIDIA Corporation",
    "lastUpdated": "15:59:58",

    "about": "NVIDIA Corporation focuses on personal computer graphics, GPUs, and artificial intelligence (AI). It operates through Graphics and Compute & Networking segments.",

    "ceo": "Jensen Huang",
    "founded": 1993,
    "headquarters": "Santa Clara, CA",

    "stats": {
      "open": 132.10,
      "prevClose": 131.10,
      "high": 136.25,
      "low": 131.50,
      "volume": "42.5M",
      "avgPrice": 134.12
    },

    "funds": 142850.00
  }
]"""

    override fun getStocks(): List<DtoStock> {
        val model = Json.decodeFromString<List<StockData>>(json)
        return model.map { it.toDomain() }
    }

    override fun getStocksDetails(symbol: String): DtoStockDetails {
        val model = Json.decodeFromString<List<StockDetailsData>>(jsonDetails)
        return model.find { it.symbol == symbol }?.toDomain() ?: model.first().toDomain()

    }
}