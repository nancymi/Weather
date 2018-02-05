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
        Forecast(_id, date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList): CityForecast = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, dailyForecast: Forecast): DayForecast
            = with(dailyForecast) { DayForecast(date, description, high, low, iconUrl, cityId) }
}
