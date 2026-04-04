package com.app.aero.data

import com.app.aero.domain.model.DtoStock
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

    override fun getStocks(): List<DtoStock> {
        val model = Json.decodeFromString<List<StockData>>(json)
        return model.map { it.toDomain() }
    }
}