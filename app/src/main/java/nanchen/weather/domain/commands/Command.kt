package nanchen.weather.domain.commands

import nanchen.weather.domain.model.Forecast as ModelForecast

interface Command<T> {
    fun execute(): T
}
