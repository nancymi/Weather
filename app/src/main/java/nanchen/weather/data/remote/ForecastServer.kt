package nanchen.weather.data.remote

import nanchen.weather.data.local.ForecastLocal
import nanchen.weather.domain.datasource.ForecastDataSource
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastLocal: ForecastLocal = ForecastLocal()): ForecastDataSource {
    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastLocal.saveForecast(converted)
        return forecastLocal.requestForecastByZipCode(zipCode, date)
    }
}