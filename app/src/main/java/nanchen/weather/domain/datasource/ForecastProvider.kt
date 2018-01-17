package nanchen.weather.domain.datasource

import nanchen.weather.data.local.ForecastLocal
import nanchen.weather.data.remote.ForecastServer
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList
import nanchen.weather.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastLocal(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun<T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}