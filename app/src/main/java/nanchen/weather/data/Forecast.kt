package nanchen.weather.data

import java.util.*

data class Forecast(val date: Date, val temperature: Float, val details: String)

var f1 = Forecast(Date(), 27.5f, "Shiny day")
var f2 = f1.copy(temperature = 230f)
//val (date, temperature, details) = f1