package nanchen.weather.domain.commands

import nanchen.weather.domain.datasource.ForecastProvider
import nanchen.weather.domain.model.Forecast

class RequestDayForecastCommand(val id: Long,
                                private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}