package nanchen.weather.data.local

import nanchen.weather.domain.datasource.ForecastDataSource
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

class ForecastLocal(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                    private val dataMapper: LocalDataMapper = LocalDataMapper()): ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}