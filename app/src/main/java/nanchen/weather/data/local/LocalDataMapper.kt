package nanchen.weather.data.local

import nanchen.weather.data.local.model.CityForecast
import nanchen.weather.data.local.model.DayForecast
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

class LocalDataMapper {
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}
