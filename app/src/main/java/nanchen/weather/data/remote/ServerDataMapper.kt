package nanchen.weather.data.remote

import nanchen.weather.data.remote.model.Forecast as RemoteForecast
import nanchen.weather.data.remote.model.ForecastResult
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {
    fun convertToDomain(zipCode: Long, forecastResult: ForecastResult) = with(forecastResult) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    fun convertForecastListToDomain(list: List<RemoteForecast>): List<Forecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    fun convertForecastItemToDomain(forecast: RemoteForecast): Forecast {
        return Forecast(-1,
                forecast.dt,
                forecast.weather[0].description,
                forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}
