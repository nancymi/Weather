package nanchen.weather.domain.commands

import nanchen.weather.data.remote.Forecast
import nanchen.weather.data.remote.ForecastRequest
import nanchen.weather.domain.model.Forecast as ModelForecast
import nanchen.weather.data.remote.ForecastResult
import nanchen.weather.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

interface Command<T> {
    fun execute(): T
}