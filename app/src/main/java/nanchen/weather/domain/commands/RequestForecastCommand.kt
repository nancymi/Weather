package nanchen.weather.domain.commands

import nanchen.weather.data.remote.ForecastRequest
import nanchen.weather.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String): Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute())
    }
}