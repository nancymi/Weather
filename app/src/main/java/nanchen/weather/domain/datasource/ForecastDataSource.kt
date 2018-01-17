package nanchen.weather.domain.datasource

import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}