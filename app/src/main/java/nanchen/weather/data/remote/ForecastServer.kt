package nanchen.weather.data.remote

import nanchen.weather.data.local.ForecastLocal
import nanchen.weather.domain.datasource.ForecastDataSource
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastLocal: ForecastLocal = ForecastLocal()): ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}